package com.cap.MyLearning.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CourseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseDetailsId;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String teacherName;
    private int maxStudents;
    private boolean completed;
    // Many-to-One relationship with Course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false) // nullable = false ekleyin
    @JsonIgnoreProperties("courseDetailsList")
    private Course course;

    @ManyToMany(mappedBy = "courseList", cascade = {CascadeType.MERGE})
    @JsonIgnoreProperties("courseList")
    private List<User> students = new ArrayList<>();

    // Getters and Setters


    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public Long getCourseDetailsId() {
        return courseDetailsId;
    }

    public void setCourseDetailsId(Long courseDetailsId) {
        this.courseDetailsId = courseDetailsId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
