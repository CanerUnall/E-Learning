package com.cap.MyLearning.repository;

import com.cap.MyLearning.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    // Belirli bir öğrenci (userId) tarafından takip edilen kursları bul
    @Query("SELECT cd.course FROM CourseDetails cd JOIN cd.students s WHERE s.userId = :userId")
    List<Course> findCoursesByStudentId(@Param("userId") Long userId);

    // Öğrencinin (userId) takip etmediği kursları bul
    @Query("SELECT c FROM Course c WHERE c.courseId NOT IN " +
            "(SELECT cd.course.courseId FROM CourseDetails cd JOIN cd.students s WHERE s.userId = :userId)")
    List<Course> findCoursesNotFollowedByStudentId(@Param("userId") Long userId);

}
