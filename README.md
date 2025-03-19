# Student Management System

A Spring Boot web application for managing student records. This application allows educators to log in, view students, add new students, and delete student records.

## Overview

This student management system provides a simple interface for educational institutions to manage their student data. Built with Spring Boot and MySQL, it offers basic CRUD operations for student records.

## Features

- **User Authentication**: Secure login system for educators
- **Student Management**:
    - View all students with their details
    - Add new students to the database
    - Delete existing student records
- **Data Persistence**: All data is stored in a MySQL database
- **Responsive UI**: Clean interface designed in Figma and implemented with HTML/CSS

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

## Front-End Development

The front-end interface was originally designed in Figma and then converted to HTML/CSS using ChatGPT. This workflow allowed for:

- Rapid prototyping and design iterations in Figma
- Efficient conversion from design to functional code
- Consistent styling across all pages

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

## Security Considerations

- Passwords are hashed using BCrypt before storage
- JDBC prepared statements are used to prevent SQL injection

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


    

