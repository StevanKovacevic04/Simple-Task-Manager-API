# 📝 Task Manager API

A simple RESTful API built with Spring Boot for managing tasks. Includes full CRUD functionality, validation, and persistence using Spring Data JPA.

## Features

- Create, read, update, and delete tasks
- Task fields: title, description, due date, priority, status
- Input validation with Bean Validation
- RESTful design with standard HTTP methods
- In-memory H2 database for quick setup

## Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

## Usage

### 🚀 Run the Application

```bash
./mvnw spring-boot:run
```

### 🧪 Access the H2 Console
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:testdb 
- Username: sa 
- Password: (leave empty)

### 🔁 Example API Endpoints
#### ✅ List all tasks
```bash
curl -X GET http://localhost:8080/api/tasks
```
#### 🔍 Get a single task
```bash
curl -X GET http://localhost:8080/api/tasks/1
```
### ➕ Create a new task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Buy groceries",
    "description": "Milk, eggs, bread",
    "dueDate": "2025-04-20",
    "priority": "HIGH",
    "status": "PENDING"
  }'
```
### ✏️ Update a task
``` bash
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Buy groceries (updated)",
    "description": "Milk, eggs, bread, and cheese",
    "dueDate": "2025-04-22",
    "priority": "MEDIUM",
    "status": "IN_PROGRESS"
  }'

```
### 🗑 Delete a task
```bash
curl -X DELETE http://localhost:8080/api/tasks/1
```