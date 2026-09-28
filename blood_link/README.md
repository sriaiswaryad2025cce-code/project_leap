# 🩸 Blood Link - Blood Donor Registration & Management System

A **Spring Boot REST API** for managing blood donors, built for the IntelliJ IDE.

---

## 🚀 Tech Stack

| Technology | Version |
|---|---|
| Java | 17 |
| Spring Boot | 3.2.5 |
| Spring Data JPA | 3.2.5 |
| MySQL | 8.x |
| Lombok | Latest |
| Maven | 3.x |

---

## 📁 Project Structure

```
blood_link/
├── src/
│   ├── main/
│   │   ├── java/com/example/blood_link/
│   │   │   ├── BloodLinkApplication.java        ← Main entry point
│   │   │   ├── controller/
│   │   │   │   └── DonorController.java         ← REST API endpoints
│   │   │   ├── service/
│   │   │   │   ├── DonorService.java            ← Service interface
│   │   │   │   └── impl/
│   │   │   │       └── DonorServiceImpl.java    ← Business logic
│   │   │   ├── repository/
│   │   │   │   └── DonorRepository.java         ← JPA repository
│   │   │   ├── model/
│   │   │   │   └── Donor.java                   ← Donor entity
│   │   │   ├── dto/
│   │   │   │   ├── DonorRequestDTO.java         ← Input DTO
│   │   │   │   ├── DonorResponseDTO.java        ← Output DTO
│   │   │   │   └── ApiResponse.java             ← Generic response wrapper
│   │   │   └── exception/
│   │   │       ├── ResourceNotFoundException.java
│   │   │       ├── DuplicateResourceException.java
│   │   │       └── GlobalExceptionHandler.java  ← Central error handler
│   │   └── resources/
│   │       └── application.properties           ← App configuration
│   └── test/
│       ├── java/com/example/blood_link/
│       │   └── BloodLinkApplicationTests.java   ← Integration tests
│       └── resources/
│           └── application-test.properties      ← H2 test config
└── pom.xml
```

---

## ⚙️ Setup Instructions (IntelliJ IDE)

### 1. Prerequisites
- Java 17 installed
- MySQL 8.x running
- IntelliJ IDEA (Community or Ultimate)

### 2. Database Setup
Open MySQL and run:
```sql
CREATE DATABASE blood_link_db;
```

### 3. Configure MySQL Credentials
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

### 4. Run in IntelliJ
- Open IntelliJ → **File → Open** → select the `blood_link` folder
- Wait for Maven to import dependencies
- Right-click `BloodLinkApplication.java` → **Run**

### 5. Verify
Visit: `http://localhost:8080/api/v1/donors`

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/donors` | Register a new donor |
| `GET` | `/api/v1/donors` | Get all donors |
| `GET` | `/api/v1/donors/{id}` | Get donor by ID |
| `PUT` | `/api/v1/donors/{id}` | Update donor |
| `DELETE` | `/api/v1/donors/{id}` | Delete donor |
| `GET` | `/api/v1/donors/blood-group/{group}` | Get donors by blood group |
| `GET` | `/api/v1/donors/search?bloodGroup=O+&city=Chennai` | Search available donors |
| `GET` | `/api/v1/donors/name-search?name=Ravi` | Search donors by name |
| `PATCH` | `/api/v1/donors/{id}/toggle-availability` | Toggle availability |
| `GET` | `/api/v1/donors/statistics` | Get donor statistics |

---

## 📝 Sample API Usage

### Register a Donor
```http
POST http://localhost:8080/api/v1/donors
Content-Type: application/json

{
  "firstName": "Ravi",
  "lastName": "Kumar",
  "dateOfBirth": "1990-05-15",
  "gender": "MALE",
  "bloodGroup": "O+",
  "phoneNumber": "9876543210",
  "email": "ravi.kumar@example.com",
  "city": "Chennai",
  "state": "Tamil Nadu",
  "pincode": "600001",
  "weightKg": 70.0,
  "isAvailable": true
}
```

### Response
```json
{
  "success": true,
  "message": "Donor registered successfully! 🩸",
  "data": {
    "id": 1,
    "fullName": "Ravi Kumar",
    "bloodGroup": "O+",
    "city": "Chennai",
    "isAvailable": true,
    "registeredAt": "2024-01-15T10:30:00"
  },
  "timestamp": "2024-01-15T10:30:00"
}
```

---

## 🧪 Running Tests
```bash
./mvnw test
```
Tests use H2 in-memory database — **no MySQL needed** for tests.

---

## 🩸 Valid Blood Groups
`A+`, `A-`, `B+`, `B-`, `AB+`, `AB-`, `O+`, `O-`

---

## 👩‍💻 Developer
**Sri Aiswarya D** — BloodLink Project
