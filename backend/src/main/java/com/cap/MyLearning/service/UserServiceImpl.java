package com.cap.MyLearning.service;

import com.cap.MyLearning.model.CourseDetails;
import com.cap.MyLearning.model.User;
import com.cap.MyLearning.model.UserRole;
import com.cap.MyLearning.repository.CourseDetailsRepository;
import com.cap.MyLearning.repository.UserRepository;
import com.cap.MyLearning.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl  implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
   // private final CourseRepository courseRepository;
    private final CourseDetailsRepository courseDetailsRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, CourseDetailsRepository courseDetailsRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
       // this.courseRepository = courseRepository;
        this.courseDetailsRepository = courseDetailsRepository;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User createUser(User user) {
        UserRole role = userRoleRepository.findById(user.getRole().getUserRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + user.getRole().getUserRoleId()));
        user.setRole(role);
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUserName(updatedUser.getUserName());
                    user.setLastName(updatedUser.getLastName());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    user.setPassword(updatedUser.getPassword());
                    user.setRole(updatedUser.getRole());
                    user.setDepartment(updatedUser.getDepartment());
                    user.setCourseList(updatedUser.getCourseList());
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Write logic to fetch customer from DB
        com.cap.MyLearning.model.User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Customer not found with email: " + email));
    }

    @Override
    public User registerForCourse(Long studentId, Long courseDetailsId) {
        // Öğrenciyi bul
        Optional<User> studentOpt = userRepository.findById(studentId);

        // CourseDetails'i bul
        Optional<CourseDetails> courseDetailsOpt = courseDetailsRepository.findById(courseDetailsId);

        if (studentOpt.isPresent() && courseDetailsOpt.isPresent()) {
            User student = studentOpt.get();
            CourseDetails courseDetails = courseDetailsOpt.get();

            // Kapasite kontrolü
            if (courseDetails.getStudents().size() >= courseDetails.getMaxStudents()) {
                throw new RuntimeException("This schedule is full.");
            }

            // Öğrencinin bu CourseDetails'e zaten kayıtlı olup olmadığını kontrol edin
            boolean alreadyRegistered = student.getCourseList().stream()
                    .anyMatch(registeredDetails -> registeredDetails.getCourseDetailsId().equals(courseDetailsId));

            if (alreadyRegistered) {
                throw new RuntimeException("Student is already registered for this schedule.");
            }

            // Öğrenciyi bu programa kaydedin
            student.getCourseList().add(courseDetails);

            // Ders programı altına öğrenciyi ekleyin
            courseDetails.getStudents().add(student);

            // Öğrenciyi ve programı kaydedin
            userRepository.save(student);
            courseDetailsRepository.save(courseDetails);

            return student;
        } else {
            throw new RuntimeException("Student or CourseDetails not found.");
        }
    }

    @Override
    public List<CourseDetails> getRegisteredCourseDetails(Long studentId, Long courseId) {
        Optional<User> studentOpt = userRepository.findById(studentId);

        if (studentOpt.isPresent()) {
            User student = studentOpt.get();

            // Öğrencinin kayıtlı olduğu CourseDetails'leri filtrele
            return student.getCourseList().stream()
                    .filter(details -> details.getCourse().getCourseId().equals(courseId))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Student not found.");
        }
    }


//    public String registerToCourse(Long userId, Long courseId) {
//        // User
//        Optional<User> optionalUser = userRepository.findById(userId);
//        if (!optionalUser.isPresent()) {
//            return "User not found!";
//        }
//
//        // Course
//        Optional<Course> optionalCourse = courseRepository.findById(courseId);
//        if (!optionalCourse.isPresent()) {
//            return "Kurs bulunamadı!";
//        }
//
//        // Öğrenci ve kursu ilişkilendir
//        User student = optionalUser.get();
//        Course course = optionalCourse.get();
//
//        // Eğer öğrenci daha önce kayıt olmadıysa kursu ekle
//        if (!student.getCourseList().contains(course)) {
//            student.getCourseList().add(course);
//            userRepository.save(student);
//            return "Register is completed successfully!";
//        } else {
//            return "User is already registered!";
//        }
//    }
}
