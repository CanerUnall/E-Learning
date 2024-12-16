package com.cap.MyLearning.service;

import com.cap.MyLearning.model.CourseDetails;
import com.cap.MyLearning.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

    User findByEmail(String email);

    User registerForCourse(Long userId, Long courseId);

    List<CourseDetails> getRegisteredCourseDetails(Long studentId, Long courseId);
}
