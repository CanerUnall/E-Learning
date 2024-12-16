package com.cap.MyLearning.controller;

import com.cap.MyLearning.model.CourseDetails;
import com.cap.MyLearning.model.User;
import com.cap.MyLearning.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    //@PreAuthorize("hasAnyAuthority('Admin')")

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (user.getRole() == null || user.getRole().getUserRoleId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    //@PatchMapping -> second way
    //@PreAuthorize("hasAnyAuthority('Admin')")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
    //@PreAuthorize("hasAnyAuthority('Admin')")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{studentId}/courseDetails/{courseDetailsId}")
    public ResponseEntity<User> registerToCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseDetailsId) {
        User result = userService.registerForCourse(studentId, courseDetailsId);
        return new ResponseEntity<User>(result, HttpStatus.OK);
    }

    // Öğrencinin kayıtlı olduğu programları (CourseDetails) dönen yeni bir endpoint
    @GetMapping("/{studentId}/registered-courseDetails/{courseId}")
    public ResponseEntity<List<CourseDetails>> getRegisteredCourseDetails(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        List<CourseDetails> registeredDetails = userService.getRegisteredCourseDetails(studentId, courseId);
        return new ResponseEntity<>(registeredDetails, HttpStatus.OK);
    }



}
