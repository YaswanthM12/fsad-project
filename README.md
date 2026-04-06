# LoanHub Full-Stack Project (React + Spring Boot)

This repository now runs as:

- **Frontend:** React + Vite in `frontend/`
- **Backend:** Spring Boot REST API in `backend/`

The old mock backend under `frontend/backend` and offline localStorage fallback logic have been removed.

## 1) Quick Start (Run Both Apps)

## Prerequisites

- Node.js 20+
- Java 21+
- Maven wrapper (already included in `backend/mvnw`)

## Step A — Start the backend

```bash
cd backend
sh mvnw spring-boot:run
```

Backend runs at `http://localhost:8080` and API at `http://localhost:8080/api`.

Health check:

```bash
curl http://localhost:8080/api/health
```

Expected response:

```json
{"status":"ok"}
```

## Step B — Start the frontend

Open a second terminal:

```bash
cd frontend
cp .env.example .env
npm install
npm run dev
```

Frontend runs at `http://localhost:5173`.

## Step C — Test login flow

Use one of the seeded backend accounts:

- Admin: `admin@example.com`
- Lender: `lender@example.com`
- Borrower: `borrower@example.com`
- Analyst: `analyst@example.com`
- Password for all: `password123`

After login, each role should navigate to its dashboard.

## 2) API Contract (Implemented)

### Auth
- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/auth/me`

### Loan domain
- `GET /api/loans`
- `GET /api/offers`
- `POST /api/offers`
- `GET /api/applications`
- `POST /api/applications`
- `PUT /api/applications/{id}/approve`
- `PUT /api/applications/{id}/reject`
- `POST /api/loans/{id}/payments`

All protected endpoints require:

```http
Authorization: Bearer <token>
```

## 3) Notes

- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- CORS is configured for `http://localhost:5173` by default.
- This backend currently uses in-memory storage with seeded data (non-persistent). Restarting backend resets data.
