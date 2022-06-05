package com.example.geektrust.domain.Entities;


import com.example.geektrust.domain.inputlayer.mapperobjects.CourseMapper;

public class Course {
    private String id;

    public boolean isAllotted() {
        return isAllotted;
    }

    public void setAllotted() {
        setAllotted(true);
    }

    private boolean isAllotted = false;
    private String name;
    private String instructor;
    private String date;
    private int min;
    private int max;

    public Course(CourseMapper mapper) {
        this.setName(mapper.getName());
        this.setInstructor(mapper.getInstructor());
        this.setDate(mapper.getDate());
        this.setMin(mapper.getMin());
        this.setMax(mapper.getMax());
    }

    public Course(String name, String instructor, String date, int min, int max) {
        this.setName(name);
        this.setInstructor(instructor);
        this.setDate(date);
        this.setMin(min);
        this.setMax(max);
    }

    public void setCourseId(String id) {
        this.setId(id);
    }

    public String getCourseId() {
        return getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAllotted(boolean allotted) {
        isAllotted = allotted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
