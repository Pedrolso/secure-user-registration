## 🔐 Secure User Registration API

A **Spring Boot REST API** that manages user registration with **role-based access control** using **Spring Security (Basic Auth)** and **BCrypt password encryption**.

This project implements a layered architecture and focuses on secure user creation, formatted input, and centralized exception handling.

---

## 🚀 Features

- User registration, listing, update, and deletion  
- **Role-based access control** (`ROLE_ADMIN`, `ROLE_USER`)  
- Password encryption with **BCrypt**  
- Centralized **exception handling** (`@ControllerAdvice`)  
- Input formatting for names and emails  
- Clean project structure following best practices  

---

## 🧩 Authorization Rules

| HTTP Method | Endpoint | Allowed Roles |
|--------------|-----------|----------------|
| `POST` | `/usuario` | `ADMIN` only *(must disable auth temporarily to create first admin)* |
| `GET` | `/usuario` | `ADMIN`, `USER` |
| `PUT` | `/usuario` | `ADMIN` |
| `DELETE` | `/usuario` | `ADMIN` |

**🔸Note:**  
To create the **first ADMIN user**, you must temporarily allow public access in your `WebSecurityConfig`:
```java
.requestMatchers(HttpMethod.POST, "/usuario").permitAll()
```
After that, revert it to .hasRole("ADMIN") for proper security.

## 🧠 Architecture Overview
```text
src/
 ├── config/          # Security and authentication configuration
 ├── controller/      # REST endpoints
 ├── DTO/             # Request and Response Data Transfer Objects
 ├── entity/          # JPA entities (User, UserRole)
 ├── exception/       # Custom exceptions and global error handler
 ├── repository/      # Database access layer
 ├── service/         # Business logic layer
 ├── utils/           # Input formatters (e.g., capitalize names)
 └── SecureUserRegistrationApplication.java
```

## ⚙️ Tech Stack

- Java 17
- Spring Boot 3
- Lombok
- Spring Security (Basic Auth)
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven

## 🧩 Example of Error Response
```java
{
  "error": "Resource Not Found",
  "message": "User ID not found 59f8def6-2769-4fbd-8f87-4b30d7b1b2ae",
  "timestamp": "2025-10-30T15:37:56.514",
  "status": 404
}
```
## 🔐 Security Configuration Example
```java
http
    .csrf(csrf -> csrf.disable())
    .authorizeHttpRequests(auth -> auth
        .requestMatchers(HttpMethod.POST, "/usuario").hasRole("ADMIN")
        .requestMatchers(HttpMethod.DELETE, "/usuario").hasRole("ADMIN")
        .requestMatchers(HttpMethod.PUT, "/usuario").hasRole("ADMIN")
        .requestMatchers(HttpMethod.GET, "/usuario").hasAnyRole("ADMIN", "USER")
        .anyRequest().authenticated()
    )
    .httpBasic(Customizer.withDefaults());
```
