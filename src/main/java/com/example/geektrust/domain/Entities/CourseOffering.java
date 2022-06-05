package com.example.geektrust.domain.Entities;

import java.util.HashMap;
import java.util.Map;

public class CourseOffering
{
    public String id;
    public Map<String, Employee> registrantMap;
    public Course course;
    CourseOffering(Course course){
        //Generate id
        this.course = course;
        registrantMap = new HashMap<>(course.max);


}
}
