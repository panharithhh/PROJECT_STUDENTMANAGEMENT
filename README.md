# Student Management System

A Spring Boot web application for managing student records. This application allows educators to log in, view students, add new students, and delete student records.

## Table of Contents

- [Overview](#overview)
- [System Requirements](#system-requirements)
- [Getting the Source Code](#getting-the-source-code)
- [Setup and Configuration](#setup-and-configuration)
- [Database Structure](#database-structure)
- [Data Structure](#data-structure)
- [Class Relationships](#class-relationships)
- [Project Structure](#project-structure)
- [Features](#features)
- [Front-End Development](#front-end-development)
- [Security Considerations](#security-considerations)
- [Future Enhancements](#future-enhancements)
- [License](#license)
- [Contributors](#contributors)

## Overview

This student management system provides a simple interface for educational institutions to manage their student data. Built with Spring Boot and MySQL, it offers basic CRUD operations for student records.

## System Requirements

- Java 17 or higher
- MySQL 5.7 or higher
- Maven 3.6 or higher
- Web browser (Chrome, Firefox, Edge, or Safari)
- Minimum 2GB RAM
- 500MB available disk space

## Getting the Source Code

You can obtain the source code using one of the following methods:

1. **Clone from GitHub**:
```
git clone https://github.com/panharithhh/SE_PROJECT.git
```



## Setup and Configuration

### Prerequisites

- Java 17 or higher
- MySQL
- Maven

### Configuration

When you fork this project in IntelliJ CE, you'll need to modify the following:

1. **SQL Table Names**: Adjust table names in the code if your database schema differs
2. **Database Connection**:
    - Update the database URL in application.properties
    - Set your database username and password

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_schema_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

3. **Environment Variables**:
    - Set the DB_PASSWORD environment variable in your run configuration

### Running the Application

1. Clone the repository
2. Configure your database connection
3. Run the application using Maven:
```
mvn spring-boot:run
```
4. Navigate to http://localhost:8080/login in your browser

## Database Structure

The application uses a MySQL database with the following primary tables:

1. **users**
    - user_id (Primary Key)
    - username
    - password (hashed with BCrypt)
    - role
    - email

2. **students**
    - student_id (Primary Key)
    - full_name
    - email
    - course
    - academic_year

3. **courses**
    - course_id (Primary Key)
    - course_name
    - description
    - credit_hours

4. **enrollments**
    - enrollment_id (Primary Key)
    - student_id (Foreign Key)
    - course_id (Foreign Key)
    - enrollment_date

## Data Structure

The application uses several key data models:

1. **Student**
    - Student ID
    - Full Name
    - Email
    - Course
    - Academic Year

2. **User**
    - Username
    - Password (hashed)
    - Role
    - Email

3. **Course**
    - Course ID
    - Course Name
    - Description
    - Credit Hours

## Class Relationships

The application follows an MVC architecture with the following key relationships:

1. **Controllers and Views**:
    - LoginController interacts with the login view
    - ManageStudentController interacts with the manage_student view
    - ManageController provides navigation between different views

2. **Models and Controllers**:
    - Controllers access and manipulate models (Student, User, etc.)
    - Data is passed between controllers and views using model attributes

3. **Models and Database**:
    - Models are mapped to database tables
    - Repositories provide data access methods for models

## Project Structure

### Controllers

- **LoginController**: Handles user authentication
- **ManageStudentController**: Manages student CRUD operations
- **ManageController**: Provides navigation mappings for different sections of the application

### Models

- **Student**: Represents a student entity with properties:
    - Student ID
    - Full Name
    - Email
    - Course
    - Academic Year

### Views

- **login**: Login form for educators
- **manage_student**: Main interface for student management
- Various other views for different sections (create_courses, forum, attendance, quiz, progress)

## Features

- **User Authentication**: Secure login system for educators
- **Student Management**:
    - View all students with their details
    - Add new students to the database
    - Delete existing student records
- **Data Persistence**: All data is stored in a MySQL database
- **Responsive UI**: Clean interface designed in Figma and implemented with HTML/CSS

## Front-End Development

The front-end interface was originally designed in Figma and then converted to HTML/CSS using ChatGPT. This workflow allowed for:

- Rapid prototyping and design iterations in Figma
- Efficient conversion from design to functional code
- Consistent styling across all pages

## Security Considerations

- Passwords are hashed using BCrypt before storage
- JDBC prepared statements are used to prevent SQL injection
- Session management for user authentication
- Input validation to prevent XSS attacks

## Future Enhancements

- Student search functionality
- Edit student information
- Pagination for large datasets
- Role-based access control
- Student attendance tracking
- Course management

## License

MIT License

## Contributors

- Chea Panharith
- Hen Chendarvottey