
CREATE TABLE educators (
    educator_id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,

);

CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    course VARCHAR(255) NOT NULL,
    academic_year VARCHAR(50) NOT NULL
);

-- Select data to verify
SELECT * FROM students;
ALTER TABLE students ADD COLUMN attendance INT DEFAULT 0;
ALTER TABLE students ADD COLUMN attendance_percentage DECIMAL(5,2) DEFAULT 0.00;
-- Select data to verify ( i don't think score here will wrk)
create table scores(

	student_id int,
	full_name varchar(254),
    homework double not null,
    midterm double not null,
    final double not null,
    project double not null,
    foreign key(student_id) references students(student_id) on delete cascade

    );


    create table attendance (

    	student_id int,
        full_name varchar(100),
        attendance int,
    	foreign key(student_id) references students(student_id) on delete cascade

    );