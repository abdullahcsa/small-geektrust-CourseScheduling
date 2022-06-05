package com.example.geektrust.domain.data;

import com.example.geektrust.domain.Entities.Employee;

import java.util.Optional;

public interface RegistrationData {
//    boolean registerEmployeeDataForCourse(String registrationID, String courseId);

    boolean registerEmployeeDataForCourse(String registrationID, String courseId, Employee employeeId);

    boolean cancelRegistration(String registrationID);

    Optional<String> getCourseId(String registrationID);

    Employee getEmployee(String registrationID);
}
