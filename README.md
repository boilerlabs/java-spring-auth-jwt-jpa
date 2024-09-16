# 🚀 Auth JWT Java Spring Boot

A boilerplate starter project for building a Java Spring Boot application with JWT authentication. This template includes essential features for implementing secure and scalable authentication systems in your Spring Boot applications.

## Table of Contents
1. [Features](#features)
2. [Getting Started](#getting-started)
3. [Usage](#usage)
4. [Contributing](#contributing)
5. [License](#license)

## Features
- JWT authentication for secure token-based access
- Sign up and sign in functionality
- Role-based access control
- Configured SecurityFilterChain for managing security
- Password hashing for secure storage
- RSA keys for signing and verifying JWTs

## Getting Started

### Prerequisites
- Java 17+
- Maven

### Installation
1. Clone the repository:
1. Clone the repo:

```bash
git clone https://github.com/boboilerlabs/auth-jwt-java-spring
cd auth-jwt-java-spring
```

2. Install dependencies:

```bash
mvn install
```

3. Run the app:

```bash
mvn spring-boot:run
```

## Usage

### Sign Up
To sign up, send a POST request to `/api/auth/signup` with the following JSON payload:

```json
{
    "username": "user",
    "password": "password"
}
```

### Sign In

To sign in, send a POST request to `/api/auth/signin` with the following JSON payload

```json
{
    "username": "user",
    "password": "password"
}
```

The response will contain the JWT token

```json
{
    "token": "example_token"
}
```

### Accessing Protected Routes

To access a protected route, include the JWT token in the Authorization header

```bash
curl -X GET http://localhost:8080/api/user
    -H "Authorization : Bearer example_token" 

curl -X GET http://localhost:8080/api/admin
    -H "Authorization : Bearer example_token"
```

## Contributing
We welcome contributions! Please see the [CONTRIBUTING.md](./CONTRIBUTING.md) for more details.

## License
This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.