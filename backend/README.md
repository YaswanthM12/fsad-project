# Backend (Spring Boot)

## Run backend

```bash
sh mvnw spring-boot:run
```

## Default URL

- App: `http://localhost:8080`
- API base: `http://localhost:8080/api`
- Health: `GET /api/health`
- Swagger: `http://localhost:8080/swagger-ui/index.html`

## Seeded users

- `admin@example.com` / `password123`
- `lender@example.com` / `password123`
- `borrower@example.com` / `password123`
- `analyst@example.com` / `password123`

## Implemented modules

- JWT authentication (`/api/auth/login`, `/api/auth/register`, `/api/auth/me`)
- Loan offers, applications, approvals/rejections, payments
- Role checks for lender/borrower/admin actions
- CORS for frontend origin

## Notes

Data is in-memory for now (non-persistent) to provide immediate functional parity with the frontend contract.
