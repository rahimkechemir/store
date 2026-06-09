 Store — E-Commerce REST API

A Spring Boot REST API for an e-commerce store with user authentication, product management, and order handling.


Tech Stack

| Layer | Technology |
| Language | Java 25 |
| Framework | Spring Boot 4.0.5 |
| Security | Spring Security + JWT (Auth0 java-jwt 4.4.0) |
| Persistence | Spring Data JPA / Hibernate |
| Database | MySQL |
| Build | Maven |

Getting Started

Prerequisites

- Java 25+
- Maven 3.8+
- MySQL 8+

 1. Clone the repository

```bash
git clone https://github.com/your-username/store.git
cd store
```

### 2. Set up the database

Create a MySQL database named `ecommerce`:

```sql
CREATE DATABASE ecommerce;
```

### 3. Configure application properties

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

jwt.algorithm.key=YourSecretKeyHere
jwt.issuer=eCommerce
jwt.expiryInSeconds=604800
```

> **Important:** Never commit real credentials or secret keys to version control. Use environment variables or a secrets manager in production.

### 4. Run the application

```bash
./mvnw spring-boot:run
```

The server starts on `http://localhost:8080`.

---

## API Endpoints

### Authentication

| Method | Endpoint | Description | Auth required |
|--------|----------|-------------|---------------|
| `POST` | `/auth/register` | Register a new user | No |
| `POST` | `/auth/login` | Login and receive a JWT | No |
| `GET` | `/auth/me` | Get current user profile | Yes |

#### Register — `POST /auth/register`

```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "securePassword123"
}
```

Returns `200 OK` on success, `409 Conflict` if username or email already exists.

#### Login — `POST /auth/login`

```json
{
  "username": "john_doe",
  "password": "securePassword123"
}
```

Returns a JWT token:

```json
{
  "jwt": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

Returns `400 Bad Request` if credentials are invalid.

#### Get Profile — `GET /auth/me`

Include the JWT in the `Authorization` header:

```
Authorization: Bearer <your_jwt_token>
```

---

## Data Models

- **LocalUser** — registered user with username, email, and encrypted password
- **Product** — store item with name, short/long description, price, and inventory link
- **Inventory** — tracks stock for a product (one-to-one with Product)
- **Address** — delivery address belonging to a user
- **Weborder** — an order linking a user, product, and address
- **WebOrderQuantities** — line-item quantities within an order

---

## Security

- Passwords are hashed using BCrypt with configurable salt rounds (`encryption.salt.rounds`).
- Protected routes require a valid JWT in the `Authorization: Bearer` header.
- JWT tokens expire after 7 days (604800 seconds) by default.

---

## Project Structure

```
src/main/java/com/rahim/store/
├── StoreApplication.java
├── api/
│   ├── controller/auth/    # AuthenticationController
│   ├── model/              # Request/response bodies (LoginBody, RegistrationBody, LoginResponse)
│   └── security/           # JWTRequestFilter, WebSecurityConfig
├── exception/              # UserAlreadyExistsException
├── model/                  # JPA entities (LocalUser, Product, Inventory, Address, Weborder, WebOrderQuantities)
│   └── dao/                # Spring Data repositories
└── service/                # UserService, JWTService, EncryptionService


This project is currently unlicensed. Add a `LICENSE` file to specify terms.
