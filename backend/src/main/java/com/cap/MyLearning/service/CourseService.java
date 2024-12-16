package com.cap.MyLearning.service;

import com.cap.MyLearning.model.Course;
import com.cap.MyLearning.model.CourseDetails;

import java.util.List;
import java.util.Optional;


public interface CourseService {
    List<Course> getAllCourses();
    Optional<Course> getCourseById(Long id);
    Course createCourse(Course course);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
    Course addCourseDateTime(Long courseId, CourseDetails newDateTime);

//    List<Course> getCoursesByStudent(Long studentId);
//
//    List<Course> getCoursesNotFollowedByStudent(Long studentId);

    Course addCourseDetails(Long courseId, CourseDetails newDetails);

    List<Course> getCoursesByStudent(Long studentId);

    List<Course> getCoursesNotFollowedByStudent(Long studentId);
}
