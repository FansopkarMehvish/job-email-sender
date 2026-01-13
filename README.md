# Recruitment Email Automation – Spring Boot

This Spring Boot application automates the process of sending resume emails to recruitment agencies using a recruiter email list stored in an Excel file.  
It uses **Thymeleaf templates** for professional email content and supports **bulk email sending with resume attachment**.

---

## 🚀 Features

- Bulk email sending to recruiters
- Recruiter email list read from Excel (`.xlsx`)
- Single-column Excel format (email IDs only)
- Resume attachment (PDF/DOC/DOCX)
- Professional HTML email using Thymeleaf
- SMTP-based email delivery (Gmail supported)
- Configurable file paths via `application.properties`
- Simple REST API trigger

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Mail (`JavaMailSender`)
- Thymeleaf
- Apache POI (Excel processing)
- Maven

---

## 📄 Excel File Format

The Excel file must:
- Be `.xlsx`
- Contain **only one column**
- Have **no header**
- Include **one email per row**

Example:

| A |
|---|
| careers@company.ae |
| hr@recruiter.com |
| jobs@agency.ae |

---

👤 Author

Mehvish Fansopkar
Java Backend Developer

