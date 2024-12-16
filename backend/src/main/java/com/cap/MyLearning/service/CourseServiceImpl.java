package com.cap.MyLearning.service;

import com.cap.MyLearning.model.Course;
import com.cap.MyLearning.model.CourseDetails;
import com.cap.MyLearning.repository.CourseDetailsRepository;
import com.cap.MyLearning.repository.CourseRepository;
import com.cap.MyLearning.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final CourseDetailsRepository courseDetailsRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, CourseDetailsRepository courseDetailsRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseDetailsRepository = courseDetailsRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setCourseName(updatedCourse.getCourseName());
                    course.setDescription(updatedCourse.getDescription());
                    course.setDayPartsOfCourse(updatedCourse.getDayPartsOfCourse());
                    course.setOnline(updatedCourse.isOnline());
                    course.setMaterialLanguage(updatedCourse.getMaterialLanguage());
                    course.setSpokenLanguage(updatedCourse.getSpokenLanguage());
                    course.setLocation(updatedCourse.getLocation());
                    course.setImage(updatedCourse.getImage());

                    return courseRepository.save(course);
                }).orElseThrow(() -> new RuntimeException("Course not found"));
    }
    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course addCourseDateTime(Long courseId, CourseDetails newDateTime) {
        return null;
    }


    @Override
    public List<Course> getCoursesByStudent(Long studentId) {
        return courseRepository.findCoursesByStudentId(studentId);
    }

    @Override
    public List<Course> getCoursesNotFollowedByStudent(Long studentId) {
        return courseRepository.findCoursesNotFollowedByStudentId(studentId);
    }

    @Override
    public Course addCourseDetails(Long courseId, CourseDetails newDetails) {
        // Find the course by ID
        Optional<Course> courseOpt = courseRepository.findById(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();

            // Set the relationship
            newDetails.setCourse(course);

            // Save the new CourseDetails
            courseDetailsRepository.save(newDetails);

            // Add the new details to the course's list
            course.getCourseDetailsList().add(newDetails);

            // Save and return the updated course
            return courseRepository.save(course);
        } else {
            throw new RuntimeException("Course not found with ID: " + courseId);
        }
    }

}
