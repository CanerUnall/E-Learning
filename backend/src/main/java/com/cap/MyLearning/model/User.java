package com.cap.MyLearning.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    @ManyToOne
    private UserRole role;
    private String department;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JsonIgnoreProperties("students")
    @JoinTable(
            name = "student_course_details",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "courseDetailsId")
    )
    private List<CourseDetails> courseList = new ArrayList<>();

    public List<CourseDetails> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseDetails> courseList) {
        this.courseList = courseList;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

//    public List<Course> getCourseList() {
//        return courseList;
//    }
//
//    public void setCourseList(List<Course> courseList) {
//        this.courseList = courseList;
//    }


    public User(Long userId, String userName, String lastName, String email, String phone, String password, UserRole role, String department, List<CourseDetails> registeredCourseDetails) {
        this.userId = userId;
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.department = department;
        this.courseList = registeredCourseDetails;
    }

    public User() {
    }
}
