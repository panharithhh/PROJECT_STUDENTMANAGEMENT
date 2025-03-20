package com.example.ez12345.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class CreateCourseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static class Course {
        private int id;
        private String courseName;
        private String courseId;
        private String description;

        public Course() {
        }

        public Course(int id, String courseName, String courseId, String description) {
            this.id = id;
            this.courseName = courseName;
            this.courseId = courseId;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    @GetMapping("/create_courses")
    public String createCoursesPage(Model model) {
        try {
            List<Course> courses = getCourses();
            model.addAttribute("courses", courses);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to load courses: " + e.getMessage());
        }
        return "create_courses";
    }

    @PostMapping("/add-course")
    public String addCourse(@RequestParam("courseName") String courseName,
                            @RequestParam("courseId") String courseId,
                            @RequestParam("description") String description,
                            Model model) {
        try {
            String sql = "INSERT INTO courses (course_title, description) VALUES (?, ?)";
            jdbcTemplate.update(sql, courseName, description);

            return "redirect:/create_courses";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to add course: " + e.getMessage());
            List<Course> courses = getCourses();
            model.addAttribute("courses", courses);
            return "create_courses";
        }
    }

    @PostMapping("/remove-course")
    public String removeCourse(@RequestParam("removeCourseId") String courseId, Model model) {
        try {
            String sql = "DELETE FROM courses WHERE course_title = ?";
            jdbcTemplate.update(sql, courseId);

            return "redirect:/create_courses";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Failed to remove course: " + e.getMessage());
            List<Course> courses = getCourses();
            model.addAttribute("courses", courses);
            return "create_courses";
        }
    }

    private List<Course> getCourses() {
        String sql = "SELECT course_id, course_title, description FROM courses";
        return jdbcTemplate.query(sql, new CourseRowMapper());
    }

    private static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("course_id"));
            course.setCourseName(rs.getString("course_title"));
            course.setCourseId("COURSE" + rs.getInt("course_id"));
            course.setDescription(rs.getString("description"));
            return course;
        }
    }
}


