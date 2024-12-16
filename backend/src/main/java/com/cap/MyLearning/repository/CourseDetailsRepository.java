package com.cap.MyLearning.repository;

import com.cap.MyLearning.model.CourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDetailsRepository extends JpaRepository<CourseDetails, Long> {
}
