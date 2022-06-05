package com.example.geektrust.domain.inputlayer.mapperobjects;

import com.example.geektrust.domain.inputlayer.DomainMapper;

public class CourseMapper implements DomainMapper {
    private String name;
    private String instructor;
    private String date;
    private int min;
    private int max;

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
