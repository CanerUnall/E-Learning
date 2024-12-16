package com.cap.MyLearning.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String description;
    private String dayPartsOfCourse;
    private boolean online;
    private String materialLanguage;
    private String spokenLanguage;
    private String location;
    private String image;

    // One-to-Many relationship with CourseDetails
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseDetails> courseDetailsList = new ArrayList<>();

    // Getters and Setters
    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDayPartsOfCourse() {
        return dayPartsOfCourse;
    }

    public void setDayPartsOfCourse(String dayPartsOfCourse) {
        this.dayPartsOfCourse = dayPartsOfCourse;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getMaterialLanguage() {
        return materialLanguage;
    }

    public void setMaterialLanguage(String materialLanguage) {
        this.materialLanguage = materialLanguage;
    }

    public String getSpokenLanguage() {
        return spokenLanguage;
    }

    public void setSpokenLanguage(String spokenLanguage) {
        this.spokenLanguage = spokenLanguage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<CourseDetails> getCourseDetailsList() {
        return courseDetailsList;
    }


    public void setCourseDetailsList(List<CourseDetails> courseDetailsList) {
        this.courseDetailsList = courseDetailsList;
    }
}
