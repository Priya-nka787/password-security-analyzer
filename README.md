# 🔐 Password Security Analyzer

**A Full-Stack Cybersecurity Web Application for Password Strength Analysis**

Evaluate password strength, identify common security weaknesses, and receive actionable recommendations through an interactive web interface powered by Java Spring Boot and PostgreSQL.

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-6DB33F?logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?logo=postgresql&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-Frontend-F7DF1E?logo=javascript&logoColor=black)
![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?logo=docker&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

---

## 📌 Overview

Password Security Analyzer is a web-based application developed to demonstrate practical cybersecurity concepts and full-stack application development.

It evaluates submitted passwords against configurable security rules, calculates a score between 0 and 100, classifies password strength, and provides recommendations to help users create stronger passwords.

The application integrates an interactive frontend with a Java Spring Boot backend and PostgreSQL database for storing analysis history.

### Key Principle: Privacy First

**The application does not store submitted passwords or their hashes.** It stores analysis metadata, such as the score, strength classification, password length, detected characteristics, and analysis timestamp.

---

## ✨ Features

- **Password Strength Analysis:** Calculates a score from 0 to 100.
- **Strength Classification:** Categorizes results as Weak, Medium, Strong, or Very Strong.
- **Character Analysis:** Checks uppercase letters, lowercase letters, numbers, and special characters.
- **Pattern Detection:** Identifies configured repeated-character and sequential patterns.
- **Common Password Detection:** Checks against a predefined list of common passwords.
- **Security Recommendations:** Suggests improvements based on detected weaknesses.
- **Interactive Dashboard:** Displays the score, strength indicator, requirement checks, and analysis results.
- **Password Visibility Toggle:** Allows users to show or hide the entered password.
- **Analysis History:** Retrieves previously saved analysis records from PostgreSQL.
- **REST API:** Connects the frontend with the Spring Boot backend.
- **Persistent Storage:** Uses Spring Data JPA and PostgreSQL to save analysis metadata.
- **Docker Support:** Includes a Dockerfile for containerized builds and deployment.

---

## 🖥️ Technology Stack

| Layer | Technologies |
|---|---|
| Frontend | HTML5, CSS3, JavaScript |
| Backend | Java 21, Spring Boot |
| API | REST API, JSON, HTTP |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Build Tool | Maven Wrapper |
| Development | VS Code, Git, GitHub |
| Deployment | Docker, Render |

---

## 🏗️ Architecture

```text
                 ┌─────────────────────┐
                 │        User         │
                 └──────────┬──────────┘
                            │
                            ▼
                 ┌─────────────────────┐
                 │  HTML / CSS / JS    │
                 │    Frontend UI      │
                 └──────────┬──────────┘
                            │ HTTP / JSON
                            ▼
                 ┌─────────────────────┐
                 │ Spring Boot         │
                 │ REST Controller     │
                 └──────────┬──────────┘
                            ▼
                 ┌─────────────────────┐
                 │ Password Analyzer   │
                 │ Service             │
                 └──────────┬──────────┘
                            ▼
                 ┌─────────────────────┐
                 │ Spring Data JPA     │
                 │ Repository          │
                 └──────────┬──────────┘
                            ▼
                 ┌─────────────────────┐
                 │ PostgreSQL          │
                 │ Analysis History    │
                 └─────────────────────┘
```

### Application Workflow

1. The user enters a password in the web interface.
2. The frontend sends the input to the analysis API.
3. The backend evaluates password characteristics and patterns.
4. The service calculates the security score and strength classification.
5. The backend returns the analysis result and recommendations.
6. Analysis metadata is stored in PostgreSQL.
7. The frontend displays the result and refreshes the analysis history.

---

## 🔍 Password Evaluation

The scoring engine uses configurable rules to evaluate password characteristics.

### Positive Factors

| Factor | Score Contribution |
|---|---:|
| Length of at least 8 characters | +20 |
| Length of at least 12 characters | +10 |
| Uppercase character | +15 |
| Lowercase character | +15 |
| Numeric character | +15 |
| Special character | +15 |

### Penalties

| Detected Pattern | Score Adjustment |
|---|---:|
| Repeated-character pattern | -10 |
| Sequential pattern | -10 |
| Common password | -20 |

The final score is constrained to the range of 0–100.

### Strength Classification

| Score | Classification |
|---:|---|
| 0–39 | Weak |
| 40–69 | Medium |
| 70–89 | Strong |
| 90–100 | Very Strong |

*Note: These are application-defined scoring rules, not a formal guarantee of password security. The score does not replace a comprehensive password-security assessment.*

---

## 🔌 REST API

### 1. Analyze Password

**Endpoint:** `POST /api/password/analyze`

Example request:

```json
{
  "password": "Example@123"
}
```

Example response structure:

```json
{
  "score": 90,
  "strength": "VERY STRONG",
  "length": 11,
  "uppercase": true,
  "lowercase": true,
  "number": true,
  "special": true,
  "repeated": false,
  "sequential": false,
  "common": false,
  "recommendations": []
}
```

The values above are illustrative; actual results depend on the configured scoring rules.

### 2. Retrieve Analysis History

**Endpoint:** `GET /api/password/history`

Returns previously saved analysis metadata, including available score, strength, character characteristics, record ID, and timestamp.

---

## 🗄️ Database Design

**Database:** PostgreSQL

**Main table:** `password_analysis`

The table stores analysis information, including fields such as:

- Analysis ID
- Security score
- Strength classification
- Password length
- Uppercase, lowercase, numeric, and special-character indicators
- Repeated and sequential pattern indicators
- Common-password indicator
- Analysis timestamp

The precise columns depend on the application's `PasswordAnalysis` entity.

### Data Privacy

| Data | Stored? |
|---|---|
| Original password | No |
| Password hash | No |
| Analysis metadata | Yes |

The password is processed for analysis but is not persisted by the application's analysis-history design.

---

## 📂 Project Structure

```text
password-security-analyzer/
│
├── .mvn/
│   └── wrapper/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── priyanka/
│       │           └── password_security_analyzer/
│       │               ├── PasswordSecurityAnalyzerApplication.java
│       │               ├── controller/
│       │               │   └── PasswordController.java
│       │               ├── service/
│       │               │   └── PasswordAnalyzerService.java
│       │               ├── model/
│       │               │   └── PasswordAnalysis.java
│       │               └── repository/
│       │                   └── PasswordAnalysisRepository.java
│       │
│       └── resources/
│           ├── static/
│           │   └── index.html
│           └── application.properties.example
│
├── .gitignore
├── Dockerfile
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

---

## ⚙️ Prerequisites

Install the following before running the project locally:

- Java Development Kit (JDK) 21
- PostgreSQL
- Git
- A code editor such as Visual Studio Code
- Docker (optional)

The Maven Wrapper is included, so a separate Maven installation is not required.

---

## 🚀 Getting Started

### 1. Clone the Repository

Replace the placeholder with your actual GitHub repository URL.

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd password-security-analyzer
```

### 2. Create the Database

Create a PostgreSQL database named:

```text
password_analyzer
```

For example, from the PostgreSQL command-line client:

```sql
CREATE DATABASE password_analyzer;
```

### 3. Configure Database Credentials

Create a local file at:

`src/main/resources/application.properties`

Add the following configuration and replace the placeholder with your local database password:

```properties
spring.application.name=password-security-analyzer

spring.datasource.url=jdbc:postgresql://localhost:5432/password_analyzer
spring.datasource.username=postgres
spring.datasource.password=YOUR_DATABASE_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

**Important:** Never commit real database credentials to GitHub. Keep the actual `application.properties` file excluded through `.gitignore`.

### 4. Run the Application

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, build the application:

```powershell
.\mvnw.cmd clean package -DskipTests
```

Run the generated JAR:

```powershell
java -jar target/<GENERATED_JAR_NAME>.jar
```

### 5. Open the Application

Visit:

**http://localhost:8080**

The frontend is served by the Spring Boot application, so a separate frontend development server is not required for the standard setup.

---

## 🐳 Docker

A Dockerfile is included for building the application inside a Java 21 container.

Build the image:

```bash
docker build -t password-security-analyzer .
```

Run the container:

```bash
docker run -p 8080:8080 password-security-analyzer
```

For database-backed execution, configure the container with valid database connection settings. The PostgreSQL database must be reachable from the container.

---

## ☁️ Deployment

The project can be deployed using a container-capable hosting platform and a managed PostgreSQL database.

Example deployment architecture:

```text
GitHub Repository
       │
       ▼
Container Build
       │
       ▼
Spring Boot Web Service
       │
       ▼
Managed PostgreSQL Database
```

For production deployment:

- Configure database connection settings using environment variables.
- Keep credentials out of source control.
- Ensure the web service can connect to the database.
- Configure the application to listen on the port required by the hosting platform.
- Use HTTPS and appropriate security controls.

**Live Demo:** Add the public application URL here once deployment is complete.

---

## 🔐 Security Considerations

- Submitted passwords are not stored in the analysis-history database.
- Database credentials are excluded from the public repository.
- Password analysis is rule-based and does not guarantee resistance to every attack.
- The common-password check uses a predefined list and is not equivalent to checking all known breached passwords.
- A production deployment should implement suitable request-size limits, rate limiting, secure transport, monitoring, and error handling.
- Avoid entering passwords used for important personal or financial accounts into a demonstration application.

---

## 🧠 Learning Outcomes

This project demonstrates practical application of:

- Java programming and object-oriented principles
- Spring Boot application development
- REST API design and implementation
- Frontend-backend integration
- JSON request and response handling
- Spring Data JPA and Hibernate
- PostgreSQL database connectivity
- Rule-based password security analysis
- Data minimization and secure configuration
- Git and GitHub version control
- Docker-based application packaging
- Cloud deployment concepts

---

## 🔮 Future Enhancements

Potential improvements include:

- Password entropy estimation
- Passphrase and password generation
- Expanded password-pattern detection
- Breached-password checks using privacy-preserving methods
- Rate limiting and API protection
- Automated unit and integration tests
- Exportable analysis reports
- Accessibility improvements
- Enhanced security monitoring

---

## 🎓 Project Information

**Project:** Password Security Analyzer  
**Category:** Cybersecurity / Full-Stack Web Development  
**Academic Context:** Advanced Java Laboratory Mini Project  
**Backend:** Java 21 and Spring Boot  
**Database:** PostgreSQL  
**Frontend:** HTML, CSS, and JavaScript  

---

## 👩‍💻 Author

**Priyanka Choudhary**  
B.Tech — Computer Science & Engineering

---

## 📄 License

This project is intended for educational and demonstration purposes. If you plan to distribute it publicly, add a `LICENSE` file and update this section to match the license you choose.

---

**Password Security Analyzer — Understand password weaknesses. Build stronger passwords. 🔐**
