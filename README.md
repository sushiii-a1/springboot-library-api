# 📚 Library Management REST API

A Spring Boot REST API for managing a library system — built with Java 21, Spring Boot 3, and MySQL.

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3
- Spring Data JPA
- MySQL
- Maven

---

## ⚙️ Setup Instructions

### 1. Prerequisites
- Java JDK 21
- MySQL Server
- Maven

### 2. Clone the Project
```bash
git clone <your-repo-url>
cd library-api
```

### 3. Create the Database
Open MySQL and run:
```sql
CREATE DATABASE library_db;
```

### 4. Configure application.properties
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

server.port=8080
```

### 5. Run the App
```bash
mvn spring-boot:run
```
The API will start on `http://localhost:8080`

---

## 📁 Project Structure

```
src/main/java/com/library/library_api/
├── LibraryApiApplication.java       ← Main entry point
├── model/
│   ├── Book.java                    ← Book entity
│   ├── Student.java                 ← Student entity
│   └── BorrowRecord.java            ← Borrow record entity
├── repository/
│   ├── BookRepository.java
│   ├── StudentRepository.java
│   └── BorrowRecordRepository.java
├── service/
│   ├── BookService.java
│   ├── StudentService.java
│   └── BorrowService.java
└── controller/
    ├── BookController.java
    ├── StudentController.java
    └── BorrowController.java
```

---

## 🔗 API Endpoints

### 📖 Books

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| GET | `/api/books/search?title=` | Search by title |
| GET | `/api/books/search?author=` | Search by author |
| GET | `/api/books/search?genre=` | Search by genre |
| POST | `/api/books` | Add a new book |
| PUT | `/api/books/{id}` | Update a book |
| DELETE | `/api/books/{id}` | Delete a book |

### 🎓 Students

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| POST | `/api/students` | Register a student |
| PUT | `/api/students/{id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete a student |

### 🔄 Borrow & Return

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/borrow?bookId=&studentId=` | Borrow a book |
| PUT | `/api/borrow/return/{borrowRecordId}` | Return a book |
| GET | `/api/borrow/student/{studentId}` | Get student borrow history |

---

## 📝 Sample Requests

### Add a Book
```json
POST /api/books
{
    "title": "Harry Potter",
    "author": "J.K. Rowling",
    "genre": "Fantasy",
    "available": true
}
```

### Add a Student
```json
POST /api/students
{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210"
}
```

### Borrow a Book
```
POST /api/borrow?bookId=1&studentId=1
```

### Return a Book
```
PUT /api/borrow/return/1
```

---

## 🗄️ Database Schema

### books
| Column | Type |
|--------|------|
| id | BIGINT (PK) |
| title | VARCHAR |
| author | VARCHAR |
| genre | VARCHAR |
| available | BOOLEAN |

### students
| Column | Type |
|--------|------|
| id | BIGINT (PK) |
| name | VARCHAR |
| email | VARCHAR |
| phone | VARCHAR |

### borrow_records
| Column | Type |
|--------|------|
| id | BIGINT (PK) |
| book_id | BIGINT (FK) |
| student_id | BIGINT (FK) |
| borrow_date | DATE |
| return_date | DATE |

---

## 👨‍💻 Author
Built this project to learn creating RestAPIs using Spring Boot and MySQL.
