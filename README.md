# Full-Stack Coupon Management System
A comprehensive Full-Stack application designed to manage business coupons, built as part of a specialized assignment to demonstrate cross-platform integration between a Spring Boot backend and a React frontend.

# 📋 Project Scope
This project showcases a complete "mini-application" that handles secure user authentication and persistent data management for coupons. It bridges the gap between client-side state management and server-side relational databases.

# ✨ Key Functionalities
Role-Based Authentication: Implemented a secure Login system with a dynamic role selection (Admin/User).

State Management: Utilized React Context API to manage and persist authenticated user data locally in the browser storage.

Full CRUD Integration: * Frontend (React): Create new coupons via POST requests and dynamically fetch/display data via GET requests.

Backend (Spring Boot): Robust API endpoints to process coupon data and manage the database lifecycle.

Persistent Storage: Integrated Spring Data JPA with a relational database (MySQL/H2) for reliable data persistence.

# 🛠️ Technical Stack
Frontend: React (JavaScript), Context API, Fetch API, CSS3.

Backend: Java, Spring Boot, Spring Data JPA, Web Starter.

Architecture: Client-Server architecture with RESTful design patterns.

# 🚀 Getting Started
Clone the project:

Bash
git clone https://github.com/miriam-borenshtain/Coupon-Management-System-FullStack.git
Server Setup:

Navigate to /coupon-system-server.

Configure your database in application.properties.

Run the Spring Boot application.

Client Setup:

Navigate to /coupon-system-client.

Install dependencies: npm install.

Start the development server: npm start.
