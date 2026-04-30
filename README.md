# 📦 Inventory Management System - Backend (Spring Boot)

https://img.shields.io/badge/Spring%2520Boot-3.1.6-green

https://img.shields.io/badge/Java-17-blue

https://img.shields.io/badge/PostgreSQL-15-blue

https://img.shields.io/badge/License-MIT-yellow

## 📋 Project Overview
Spring Boot REST API backend for an Inventory Management System designed for manufacturing businesses. This backend provides complete CRUD operations, authentication, stock management, and reporting capabilities.

## 🌐 Live Deployment
**API Base URL:** [https://inventory-backend-aq7l.onrender.com](https://inventory-backend-aq7l.onrender.com)

## ✨ Features
- 🔐 **Authentication & Authorization** - JWT-based secure access
- 📊 **Product Management** - Complete CRUD operations
- 👥 **Supplier Management** - Track supplier details
- 📈 **Stock Management** - Real-time stock in/out tracking
- 🚨 **Low Stock Alerts** - Automated notifications
- 📊 **Analytics Dashboard** - Comprehensive reports and charts
- 🛡️ **Security** - Spring Security with CORS configuration
- 🗄️ **Database** - PostgreSQL with proper schema design

## 🏗️ Tech Stack
- **Java 17** - Core programming language
- **Spring Boot 3.1.6** - Backend framework
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Database operations
- **PostgreSQL 15** - Primary database
- **JWT** - JSON Web Tokens for authentication
- **Maven** - Dependency management
- **Render** - Cloud deployment platform

## 📋 Prerequisites
Before you begin, ensure you have installed:
- Java 17 or higher
- PostgreSQL 14+
- Maven 3.6 or higher
- Git

## 🚀 Quick Start

### 1. Clone the Repository
```
git clone https://github.com/DarakshanSaara/inventory-backend
cd backend/inventory
```
### 2. Configure Database
-- Create database
```
CREATE DATABASE inventory_db;
```
-- Create user (optional)
```
CREATE USER 'inventory_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON inventory_db.* TO 'inventory_user'@'localhost';
FLUSH PRIVILEGES;
```
### 3. Configure Application
Create src/main/resources/application-local.properties:

# PostgreSQL Configuration
```
spring.datasource.url=jdbc:postgresql://localhost:5432/inventory_db
spring.datasource.username=inventory_user
spring.datasource.password=yourpassword123
spring.datasource.driver-class-name=org.postgresql.Driver
```
# JPA Configuration
```
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```
# Server Configuration
```
server.port=8080
server.servlet.context-path=/
```
# CORS Configuration
```
cors.allowed-origins=http://localhost:3000
```
### 4. Build and Run

# Clean and build
```
mvn clean package
```
# Run the application
```
mvn spring-boot:run -Dspring-boot.run.profiles=local
```
### 5. Verify Installation
Open your browser and visit:

http://localhost:8080 - Welcome page

http://localhost:8080/api/health - Health check

http://localhost:8080/api/products - Products API

## 📊 Database Schema

-- Products Table
```
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    unit VARCHAR(20),
    price DECIMAL(10,2),
    min_stock_level INT,
    current_stock INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
-- Suppliers Table
```
CREATE TABLE suppliers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    supplier_id VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```
-- Stock Transactions Table
```
CREATE TABLE stock_transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id BIGINT NOT NULL,
    transaction_type ENUM('IN', 'OUT'),
    quantity INT NOT NULL,
    notes TEXT,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```
-- Users Table
```
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20)
);
```

## 🔧 API Documentation
### Base URL
https://inventory-backend-aq7l.onrender.com/api

### Authentication Endpoints
Method	Endpoint	Description	Request Body
POST	/auth/login	User login	{"username": "admin", "password": "admin123"}

### Product Endpoints
Method	Endpoint	Description
GET	/products	Get all products
GET	/products/{id}	Get product by ID
POST	/products	Create new product
PUT	/products/{id}	Update product
DELETE	/products/{id}	Delete product
GET	/products/low-stock	Get low stock products
GET	/products/dashboard/stats	Get dashboard statistics

### Supplier Endpoints
Method	Endpoint	Description
GET	/suppliers	Get all suppliers
POST	/suppliers	Create new supplier
PUT	/suppliers/{id}	Update supplier
DELETE	/suppliers/{id}	Delete supplier

### Stock Management Endpoints
Method	Endpoint	Description
POST	/stock/in	Add stock to inventory
POST	/stock/out	Remove stock from inventory
GET	/stock/transactions	Get all transactions
GET	/stock/report	Get stock movement report

## 📦 Sample Data
The application is pre-loaded with sample data including:

### Sample Products (12 items)
Laptop
Smartphone
Steel Bolts
Plastic Pellets
Packaging Box

### Sample Suppliers (3 items)
ABC Electronics
Global Hardware
Premium Raw Materials

🗂️ Project Structure
```
src/main/java/com/zcrom/inventory/
├── InventoryManagementApplication.java   # Main application class
├── config/
│   ├── SecurityConfig.java              # Security configuration
│   └── DatabaseConfig.java              # Database access config
├── controller/
│   ├── ProductController.java           # Product REST APIs
│   ├── SupplierController.java          # Supplier REST APIs
│   ├── StockController.java             # Stock management APIs
│   ├── AuthController.java              # Authentication APIs
│   └── TestController.java              # Test APIs
├── entity/
│   ├── Product.java                     # Product entity
│   ├── Supplier.java                    # Supplier entity
│   ├── StockTransaction.java            # Stock transaction entity
│   └── User.java                        # User entity
├── repository/
│   ├── ProductRepository.java           # Product data access
│   ├── SupplierRepository.java          # Supplier data access
│   ├── StockTransactionRepository.java  # Transaction data access
│   └── UserRepository.java              # User data access
├── service/
│   ├── ProductService.java              # Product business logic
│   ├── SupplierService.java             # Supplier business logic
│   └── StockService.java                # Stock management logic
├── security/
│   ├── JwtUtil.java                     # JWT utility
│   ├── JwtAuthenticationFilter.java     # JWT filter
│   └── CustomUserDetailsService.java    # Custom user details
└── DataInitializer.java                 # Sample data initialization
```

## 📊 API Testing
```
# Test health endpoint
curl https://inventory-backend-aq7l.onrender.com/api/health

# Get all products
curl https://inventory-backend-aq7l.onrender.com/api/products

# Get dashboard statistics
curl https://inventory-backend-aq7l.onrender.com/api/products/dashboard/stats

# Create new product
curl -X POST https://inventory-backend-aq7l.onrender.com/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Product",
    "category": "Test",
    "price": 99.99,
    "minStockLevel": 10,
    "currentStock": 25
  }'
```
<!--
## 🙏 Acknowledgments
Spring Boot Team for the amazing framework

MySQL for robust database management

Render for free hosting

ZCROM Technologies for the assignment opportunity
-->

## 👨‍💻 Author
Saara Darakshan

GitHub: https://github.com/DarakshanSaara

Email: saaradarakshan56@gmail.com
