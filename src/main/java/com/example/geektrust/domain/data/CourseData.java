package com.example.geektrust.domain.data;

import com.example.geektrust.domain.Entities.Course;

import java.util.Optional;
import java.util.Set;

public interface CourseData {
    boolean persistCourse(String id, Course course);

    Optional<Course> getCourseData(String courseId);

    boolean isRegistrationOpen(String courseId);

    boolean allotCourse(String courseId);

    Set<String> getRegistrations(String courseId);

    boolean addRegistration(String courseId, String registrationID);

    boolean cancelRegistration(String courseId, String registrationID);
}
