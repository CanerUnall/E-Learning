package com.cap.MyLearning.controller;

import com.cap.MyLearning.model.Course;
import com.cap.MyLearning.model.CourseDetails;
import com.cap.MyLearning.repository.CourseRepository;
import com.cap.MyLearning.service.CourseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/courseDetails")
public class CourseDetailsController {
    private final CourseDetailsService courseDetailsService;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseDetailsController(CourseDetailsService courseDetailsService, CourseRepository courseRepository) {
        this.courseDetailsService = courseDetailsService;

        this.courseRepository = courseRepository;
    }

    // Create CourseDetails
    @PostMapping("/add/{courseId}")
    public ResponseEntity<CourseDetails> createCourseDetails(@PathVariable Long courseId, @RequestBody CourseDetails courseDetails) {
        // Course ID ile Course'u bul
        System.out.println(courseId);
        Course course = courseRepository.findById(courseId).orElseThrow();
        System.out.println(course.getCourseId());

        // Eğer Course bulunduysa, CourseDetails ile ilişkilendir

            courseDetails.setCourse(course); // Course ile ilişkiyi kur
            CourseDetails createdCourseDetails = courseDetailsService.createCourseDetails(courseDetails);
            return ResponseEntity.ok(createdCourseDetails);

    }

    // Update CourseDetails
    @PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<CourseDetails> updateCourseDetails(@PathVariable("id") Long id, @RequestBody CourseDetails courseDetails) {
        CourseDetails updatedCourseDetails = courseDetailsService.updateCourseDetails(id, courseDetails);
        return ResponseEntity.ok(updatedCourseDetails);
    }

    // Delete CourseDetails
    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteCourseDetails(@PathVariable("id") Long id) {
        courseDetailsService.deleteCourseDetails(id);
        return ResponseEntity.noContent().build();
    }


}
