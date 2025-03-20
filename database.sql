
CREATE TABLE educators (
    educator_id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,

);

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    course VARCHAR(100) NOT NULL,
    academic_year VARCHAR(10) NOT NULL,
    attendance INT DEFAULT 0,
    attendance_percentage INT DEFAULT 0,
    quiz INT DEFAULT 0,
    homework INT DEFAULT 0,
    midterm INT DEFAULT 0,
    final_exam INT DEFAULT 0,
    final_score INT DEFAULT 0,
    final_attendance INT DEFAULT 0,
    total_score INT DEFAULT 0
);
