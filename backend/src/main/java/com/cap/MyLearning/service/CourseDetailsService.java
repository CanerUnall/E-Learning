package com.cap.MyLearning.service;

import com.cap.MyLearning.model.CourseDetails;

import java.util.List;
import java.util.Optional;

public interface CourseDetailsService {
    List<CourseDetails> getAllCourseDetails();
    Optional<CourseDetails> getCourseDetailsById(Long id);
    CourseDetails updateCourseDetails(Long id, CourseDetails courseDetails);
    void deleteCourseDetails(Long id);
    public CourseDetails createCourseDetails(CourseDetails courseDetails);
}
