package com.cap.MyLearning.controller;

import com.cap.MyLearning.model.Course;
import com.cap.MyLearning.model.CourseDetails;
import com.cap.MyLearning.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping(value="/add", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        System.out.println("Received course: " + course);
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.ok(createdCourse);
    }

    //@PatchMapping -> second way
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

    @PostMapping("/{courseId}/add-details")
    public ResponseEntity<Course> addCourseDetails(
            @PathVariable Long courseId,
            @RequestBody CourseDetails newDetails) {
        Course updatedCourse = courseService.addCourseDetails(courseId, newDetails);
        return ResponseEntity.ok(updatedCourse);
    }

     //Öğrencinin takip ettiği kursları getir
    @GetMapping("/student/{studentId}/courses")
    public List<Course> getCoursesByStudent(@PathVariable Long studentId) {
        return courseService.getCoursesByStudent(studentId);
    }

    // Öğrencinin takip etmediği kursları getir
    @GetMapping("/student/{studentId}/unfollowed-courses")
    public List<Course> getUnfollowedCoursesByStudent(@PathVariable Long studentId) {
        return courseService.getCoursesNotFollowedByStudent(studentId);
    }

}
