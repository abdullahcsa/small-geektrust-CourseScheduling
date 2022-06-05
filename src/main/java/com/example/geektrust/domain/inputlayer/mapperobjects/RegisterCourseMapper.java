package com.example.geektrust.domain.inputlayer.mapperobjects;

import com.example.geektrust.domain.inputlayer.DomainMapper;

public class RegisterCourseMapper implements DomainMapper {
    private String name;
    private String email;
    private String courseId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
