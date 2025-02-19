CREATE TABLE educators (
    educator_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE courses(
	course_id int auto_increment primary key,
    educator_id int not null,
    course_title varchar(123) not null,
    description Text,
    Foreign key(educator_id) references educators(educator_id) on delete cascade);

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    teacher_id INT NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    FOREIGN KEY (teacher_id) REFERENCES educators(educator_id) ON DELETE CASCADE
);

CREATE TABLE course_enrollments (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
    student_id INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    UNIQUE (course_id, student_id)
) ;


CREATE TABLE quizzes (
    quiz_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
    quiz_title VARCHAR(255) NOT NULL,
    description TEXT,
    total_points INT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
) ;


CREATE TABLE quiz_scores (
    score_id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL,
    student_id INT NOT NULL,
    score INT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(quiz_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    UNIQUE (quiz_id, student_id)
) ;


create table forums(

    forum_id int auto_increment primary key,
    topic varchar(200) not null,
	Description text not null

	);



