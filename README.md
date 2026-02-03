# 🏋️ Fitness Tracker System

A backend-focused **Fitness Tracker Web Application** built using **Spring Boot**, designed to manage users, workouts, goals, and health metrics securely.

This project follows **industry best practices** such as environment-based configuration, secure credential handling, and RESTful API design.

---

## 🚀 Features

- User Registration & Authentication
- Secure API access (Spring Security)
- Workout & Activity Tracking
- Fitness Goals Management
- Email notifications (SMTP)
- MySQL database integration
- Environment-based configuration (no hardcoded secrets)

---

## 🛠 Tech Stack

- **Backend:** Java, Spring Boot
- **Security:** Spring Security
- **Database:** MySQL
- **ORM:** Hibernate / JPA
- **Build Tool:** Maven
- **Version Control:** Git & GitHub

---

## 📂 Project Structure

Fitness_Tracker_System/
│
├── backend/
│ ├── src/main/java
│ ├── src/main/resources
│ │ └── application.properties
│ └── pom.xml
│
├── .gitignore
├── README.md
└── .env (ignored, local only)


---

## ⚙️ Configuration

This project uses **environment variables** to manage sensitive data.

### 🔐 Environment Variables

Create a `.env` file in the project root:

```env
DB_USERNAME=root
DB_PASSWORD=your_mysql_password

MAIL_USERNAME=yourmail@gmail.com
MAIL_PASSWORD=your_app_password

ADMIN_USERNAME=admin
ADMIN_PASSWORD=admin123
⚠️ Never commit .env to GitHub

🗄 Database Setup
Create a MySQL database:

CREATE DATABASE fitness_db;
Update DB credentials in .env

Hibernate will auto-create tables on startup.

▶️ Running the Application
mvn spring-boot:run
Application runs on:

http://localhost:8080
🔒 Security Practices
No hardcoded credentials

Secrets managed via environment variables

GitHub Push Protection compliant

Production-ready configuration structure

📌 Future Enhancements
JWT Authentication

Role-based access control

Frontend integration (React)

Activity analytics dashboard

Dockerization

👨‍💻 Author
Abishek
Backend Developer | Java | Spring Boot

📜 License
This project is for learning and demonstration purposes.


---

## ✅ How to add it to GitHub

1. Create file:
README.md


2. Paste the content above

3. Commit & push:
```bash
git add README.md
git commit -m "Add project README"
git push
