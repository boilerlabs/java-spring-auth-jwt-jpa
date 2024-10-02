# 🚀 Java Spring Boot JPA JWT Auth

![License](https://img.shields.io/github/license/boilerlabs/java-spring-auth-jwt-jpa)
![Issues](https://img.shields.io/github/issues/boilerlabs/java-spring-auth-jwt-jpa)
![Stars](https://img.shields.io/github/stars/boilerlabs/java-spring-auth-jwt-jpa)

A boilerplate starter project for building a Java Spring Boot application with JWT authentication and JPA. This template includes essential features for implementing secure and scalable authentication systems in your Spring Boot applications.

## Table of Contents
1. [Features](#features)
2. [Getting Started](#getting-started)
3. [Usage](#usage)
4. [Contributing](#contributing)
5. [License](#license)

## Features
- JWT authentication for secure token-based access
- RSA keys for signing and verifying JWTs
- Role-based access control
- Configured SecurityFilterChain for managing security
- Password hashing for secure storage
- User Sign Up and Sign In endpoints
- Profile endpoint for accessing user details
- Jakarta Bean Validation for input validation
- Docker Compose for running PostgreSQL and Adminer

## Getting Started

### Prerequisites
- Java 17+
- Maven

### Installation
1. Clone the repository:

```bash
git clone https://github.com/boboilerlabs/java-spring-auth-jwt-jpa
cd java-spring-auth-jwt-jpa
```

2. Generating `app.key` and `app.pub`:
```bash
openssl genrsa -out src/main/resources/app.key 2048 
openssl rsa -in src/main/resources/app.key -pubout -out src/main/resources/app.pub
```

3. Running with Maven
```bash
mvn spring-boot:run
```

## Usage

### Sign Up
To sign up, send a POST request to `/api/auth/signup` with the following JSON payload:

```json
{
    "username": "example_username",
    "password": "example_password"
}
```

### Sign In

To sign in, send a POST request to `/api/auth/signin` with the following JSON payload

```json
{
    "username": "example_username",
    "password": "example_password"
}
```

The response will contain the JWT token

```json
{
    "token": "example_token",
    "expiration": "2024-09-19T10:00:00Z"
}
```

### Accessing Protected Routes

To access a protected route, include the JWT token in the Authorization header

```bash
curl -X GET http://localhost:8080/api/profile \
     -H "Authorization: Bearer example_token"
```

The response will return the user details:

```bash
{
    "id": "user-uuid",
    "username": "user",
    "roles": ["ROLE_USER"]
}
```

## Contributing
We welcome contributions! Please see the [CONTRIBUTING.md](./CONTRIBUTING.md) for more details.

## License
This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.