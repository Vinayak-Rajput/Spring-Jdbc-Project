# Spring Boot Database Demo

A Spring Boot employee-management REST API backed by PostgreSQL and Spring Data JPA. The project demonstrates entity persistence, DTO mapping, CRUD-style endpoints, centralized exception handling, and OpenAPI/Swagger documentation.

## Technology stack

- Java 25
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA and Hibernate
- PostgreSQL
- Maven
- Lombok
- SpringDoc OpenAPI

## Project structure

```text
SpringBootDatabaseDemo/
├── src/
│   ├── main/
│   │   ├── java/com/hdfc/
│   │   │   ├── SpringBootDatabaseDemoApplication.java
│   │   │   ├── controller/EmployeeController.java
│   │   │   ├── dto/
│   │   │   │   ├── EmployeeRequestDto.java
│   │   │   │   └── EmployeeResponseDto.java
│   │   │   ├── entity/Employee.java
│   │   │   ├── exception/
│   │   │   │   ├── EmployeeAlreadyExistException.java
│   │   │   │   ├── EmployeeNotFoundException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── mapper/EmployeeDtoMapper.java
│   │   │   ├── repository/EmployeeRepository.java
│   │   │   └── service/EmployeeService.java
│   │   └── resources/application.yaml
│   └── test/java/com/hdfc/
│       └── SpringBootDatabaseDemoApplicationTests.java
├── pom.xml
├── mvnw
├── mvnw.cmd
├── HELP.md
└── README.md
```

## Data model

The `Employee` entity is stored in the `employee` table:

| Field | Type | Database mapping |
| --- | --- | --- |
| `empId` | `Integer` | Primary key, identity-generated |
| `empName` | `String` | `emp_name` |
| `email` | `String` | `emp_email`, unique and not null |
| `address` | `String` | Default column name |
| `salary` | `Double` | Default column name |

`EmployeeResponseDto` currently exposes only `empId` and `empName`. The request DTO accepts `empId`, `empName`, `email`, `address`, and `salary`.

## Database configuration

The application reads its PostgreSQL settings from `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/employeemanagementdb
    username: <postgres-username>
    password: <postgres-password>
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

Create the `employeemanagementdb` database and provide valid local credentials before starting the application. `ddl-auto: update` allows Hibernate to update the schema from the entity model; use a migration strategy instead for production deployments.

## REST API

The base path is `/employee`. The application runs on port `8080` by default.

### Create an employee

```http
POST /employee
Content-Type: application/json
```

Request:

```json
{
  "empId": 1,
  "empName": "John",
  "email": "john@example.com",
  "address": "Hyderabad",
  "salary": 50000.0
}
```

Successful response: `201 Created`

```json
{
  "empId": 1,
  "empName": "John"
}
```

### Get an employee by ID

```http
GET /employee/{empId}
```

Successful response: `200 OK`

### Get all employees

```http
GET /employee
```

Successful response: `200 OK`

```json
[
  {
    "empId": 1,
    "empName": "John"
  }
]
```

### Update an employee

```http
PUT /employee/{empId}
Content-Type: application/json
```

The request body uses the same fields as employee creation. The controller is defined to return `200 OK` with an `EmployeeResponseDto`.

### Delete an employee

```http
DELETE /employee/{empId}
```

The controller is defined to return `204 No Content`.

### Count employees

```http
GET /employee/count
```

Successful response: `200 OK` with the employee count as a number.

## Error handling

`GlobalExceptionHandler` maps application exceptions to these responses:

| Condition | HTTP status | Response fields |
| --- | --- | --- |
| Duplicate employee ID | `409 Conflict` | `Status`, `Message` |
| Employee does not exist | `404 Not Found` | `Status`, `Message` |
| Unsupported HTTP method | `405 Method Not Allowed` | `Status`, `Message` |
| Unhandled exception | `500 Internal Server Error` | Exception message |

Example:

```json
{
  "Status": 404,
  "Message": "Employee with ID 10 does not exist."
}
```

## OpenAPI and Swagger UI

SpringDoc is included in the Maven dependencies. When the application is running, Swagger UI is available at:

```text
http://localhost:8080/swagger-ui/index.html
```

The generated OpenAPI document is available at:

```text
http://localhost:8080/v3/api-docs
```

## Running the application

From the project directory:

```bash
./mvnw spring-boot:run
```

On Windows:

```bat
mvnw.cmd spring-boot:run
```

## Building and testing

```bash
./mvnw clean package
./mvnw test
```

The test suite currently includes a Spring application-context smoke test.

## Current implementation note

The `updateEmployee` and `deleteEmployee` service methods perform their repository operation when the employee exists, but then continue to throw `EmployeeNotFoundException` because they do not return a successful result from that branch. The endpoint contracts above reflect the controller declarations; these methods should be corrected before relying on update or delete requests in a running application.

## License

This project is intended for learning and demonstration purposes.
