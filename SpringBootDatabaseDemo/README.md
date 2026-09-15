# SpringBootDatabaseDemo

A Spring Boot application that demonstrates database connectivity and CRUD operations using JPA and PostgreSQL. The project exposes REST endpoints for creating, reading, and listing employees.

## Overview

This project is a simple employee management API built with:

- Java 25
- Spring Boot 4.1.1
- Spring Data JPA
- PostgreSQL
- Maven
- SpringDoc OpenAPI (Swagger UI)

## Project Structure

```text
SpringBootDatabaseDemo/
├── src/
│   ├── main/
│   │   ├── java/com/hdfc/
│   │   │   ├── SpringBootDatabaseDemoApplication.java
│   │   │   ├── controller/
│   │   │   │   └── EmployeeController.java
│   │   │   ├── dto/
│   │   │   │   └── EmployeeResponseDto.java
│   │   │   ├── entity/
│   │   │   │   └── Employee.java
│   │   │   ├── mapper/
│   │   │   │   └── EmployeeDtoMapper.java
│   │   │   └── repository/
│   │   │       └── EmployeeRepository.java
│   │   └── resources/
│   │       └── application.yaml
│   └── test/java/com/hdfc/
│       └── SpringBootDatabaseDemoApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
├── HELP.md
└── README.md
```

## Features

- Create an employee record
- Retrieve a single employee by ID
- Retrieve all employees
- Persist data in PostgreSQL via Hibernate/JPA
- Auto-generated table creation using `ddl-auto: update`

## Database Configuration

The application is configured to connect to a local PostgreSQL database in `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/employeemanagementdb
    username: VINAYAK.R
    password: 1234
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

Before running the app, make sure PostgreSQL is installed and a database named `employeemanagementdb` exists. You can update the database credentials in this file if needed.

## Entity Model

The `Employee` entity contains:

- `empId`
- `empName`
- `email`
- `address`
- `salary`

The table name is mapped to `employee`, and the email field is marked as unique and non-null.

## REST API Endpoints

### Create an employee

- Method: `POST`
- URL: `/employee`
- Request body:

```json
{
  "empName": "John",
  "email": "john@example.com",
  "address": "Hyderabad",
  "salary": 50000.00
}
```

Response:

```json
{
  "empId": 1,
  "empName": "John"
}
```

### Get employee by ID

- Method: `GET`
- URL: `/employee/{empId}`

### Get all employees

- Method: `GET`
- URL: `/employee`

## Running the Application

From the project root (`SpringBootDatabaseDemo`), run:

```bash
./mvnw spring-boot:run
```

Or, on Windows:

```bash
mvnw.cmd spring-boot:run
```

The application will start on the default Spring Boot port:

```text
http://localhost:8080
```

## Building and Testing

To compile the project:

```bash
./mvnw clean package
```

To run the test suite:

```bash
./mvnw test
```

## Notes

- The project includes SpringDoc OpenAPI support for Swagger UI.
- The `EmployeeDtoMapper` converts the entity into a response DTO that includes only selected fields.
- This is a benchmark-style example for learning Spring Boot, JPA, and PostgreSQL integration.

## License

This project is intended for learning and demonstration purposes.
