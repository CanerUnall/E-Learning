package com.cap.MyLearning.service;

import com.cap.MyLearning.model.CourseDetails;
import com.cap.MyLearning.repository.CourseDetailsRepository;
import com.cap.MyLearning.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {
    private final CourseRepository courseRepository;
    private final CourseDetailsRepository courseDetailsRepository;

    @Autowired
    public CourseDetailsServiceImpl(CourseDetailsRepository courseDetailsRepository, CourseRepository courseRepository) {
        this.courseDetailsRepository = courseDetailsRepository;
        this.courseRepository=courseRepository;
    }

    @Override
    public CourseDetails createCourseDetails(CourseDetails courseDetails) {
        return courseDetailsRepository.save(courseDetails);
    }


    @Override
    public List<CourseDetails> getAllCourseDetails() {
        return courseDetailsRepository.findAll();
    }

    @Override
    public Optional<CourseDetails> getCourseDetailsById(Long id) {
        return courseDetailsRepository.findById(id);
    }

    // Update CourseDetails
    @Override
    public CourseDetails updateCourseDetails(Long id, CourseDetails courseDetails) {
        Optional<CourseDetails> existingDetails = courseDetailsRepository.findById(id);
        if (existingDetails.isPresent()) {
            CourseDetails detailsToUpdate = existingDetails.get();
            detailsToUpdate.setStartDate(courseDetails.getStartDate());
            detailsToUpdate.setEndDate(courseDetails.getEndDate());
            detailsToUpdate.setStartTime(courseDetails.getStartTime());
            detailsToUpdate.setEndTime(courseDetails.getEndTime());
            detailsToUpdate.setTeacherName(courseDetails.getTeacherName());
            detailsToUpdate.setMaxStudents(courseDetails.getMaxStudents());
            detailsToUpdate.setCompleted(courseDetails.isCompleted());
            return courseDetailsRepository.save(detailsToUpdate);
        } else {
            throw new RuntimeException("CourseDetails not found with id: " + id);
        }
    }

    // Delete CourseDetails
    @Override
    public void deleteCourseDetails(Long id) {
        courseDetailsRepository.deleteById(id);
    }


}


