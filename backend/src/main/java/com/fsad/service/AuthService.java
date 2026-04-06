package com.fsad.service;

import com.fsad.auth.JwtService;
import com.fsad.dto.AuthResponse;
import com.fsad.dto.LoginRequest;
import com.fsad.dto.RegisterRequest;
import com.fsad.model.Role;
import com.fsad.model.UserAccount;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {

    private final InMemoryStore store;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(InMemoryStore store, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.store = store;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse register(RegisterRequest request) {
        String normalizedEmail = request.getEmail().toLowerCase(Locale.ROOT).trim();
        boolean exists = store.users().stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(normalizedEmail));
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        Role role = request.getRole() == null ? Role.BORROWER : Role.from(request.getRole());
        UserAccount user = new UserAccount(
                "U-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase(Locale.ROOT),
                request.getName(),
                normalizedEmail,
                role,
                passwordEncoder.encode(request.getPassword())
        );
        store.users().add(user);
        return new AuthResponse(toSafeUser(user), jwtService.generateToken(user));
    }

    public AuthResponse login(LoginRequest request) {
        UserAccount user = store.users().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(request.getEmail()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        if (request.getRole() != null && !request.getRole().isBlank() && !user.getRole().toApiValue().equals(request.getRole())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Selected role does not match your account role");
        }

        return new AuthResponse(toSafeUser(user), jwtService.generateToken(user));
    }

    public Map<String, Object> getUserById(String userId) {
        UserAccount user = store.users().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid user token"));
        return toSafeUser(user);
    }

    public Map<String, Object> toSafeUser(UserAccount user) {
        Map<String, Object> safeUser = new LinkedHashMap<>();
        safeUser.put("id", user.getId());
        safeUser.put("name", user.getName());
        safeUser.put("email", user.getEmail());
        safeUser.put("role", user.getRole().toApiValue());
        return safeUser;
    }
}
