package com.example.geektrust.domain.Entities;


import com.example.geektrust.domain.inputlayer.mapperobjects.CourseMapper;

public class Course {
    String id;

    public boolean isAllotted() {
        return isAllotted;
    }

    public void setAllotted() {
        isAllotted = true;
    }

    boolean isAllotted = false;
    public String name;
    public String instructor;
    public String date;
    public int min;
    public int max;

    public Course(CourseMapper mapper) {
        this.name = mapper.name;
        this.instructor = mapper.instructor;
        this.date = mapper.date;
        this.min = mapper.min;
        this.max = mapper.max;
    }

    public Course(String name, String instructor, String date, int min, int max) {
        this.name = name;
        this.instructor = instructor;
        this.date = date;
        this.min = min;
        this.max = max;
    }

    public void setCourseId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return id;
    }
}
