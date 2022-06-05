package com.example.geektrust.persistence;

import com.example.geektrust.domain.Entities.Course;
import com.example.geektrust.domain.data.CourseData;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseDataService implements CourseData {
    // Map as replacement for data layer, single map for the application
    Map<String, Course>  courseOfferings = new HashMap<>();
    Map<String, Set<String>> courseRegistrations = new HashMap<>();
    @Override
    public boolean persistCourse(String id, Course course) {
        courseOfferings.put(id, course);
        HashSet<String> registrations = new LinkedHashSet<>(course.max);
        courseRegistrations.put(id, registrations);
        return true;
    }

    @Override
    public Optional<Course> getCourseData(String courseId) {
        return Optional.ofNullable(courseOfferings.get(courseId));
    }

    @Override
    public boolean isRegistrationOpen(String courseId){
        int currentRegistrations = courseRegistrations.get(courseId).size();
        Course course = courseOfferings.get(courseId);
        return (currentRegistrations < course.max);
    }

    @Override
    public boolean allotCourse(String courseId){
        Course course = courseOfferings.get(courseId);
        int currentRegistrations = courseRegistrations.get(courseId).size();
        if (currentRegistrations < course.min)
            return false;
        course.setAllotted();
        courseOfferings.put(courseId, course);
        return true;
    }

    @Override
    public Set<String> getRegistrations(String courseId){
        return courseRegistrations.get(courseId);
    }
    @Override
    public boolean addRegistration(String courseId, String registrationID) {
       if(!isRegistrationOpen(courseId))
            return false;
        Set<String> registrations = courseRegistrations.get(courseId);
        registrations.add(registrationID);
        courseRegistrations.put(courseId, registrations);
        return true;
    }

    @Override
    public boolean cancelRegistration(String courseId, String registrationID) {
        Set<String> registrations = courseRegistrations.get(courseId);
        registrations.remove(registrationID);
        courseRegistrations.put(courseId, registrations);
        return true;
    }

}
