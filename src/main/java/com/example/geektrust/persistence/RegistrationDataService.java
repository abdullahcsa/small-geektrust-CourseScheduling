package com.example.geektrust.persistence;

import com.example.geektrust.domain.Entities.Employee;
import com.example.geektrust.domain.data.RegistrationData;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RegistrationDataService implements RegistrationData {
    // Map as replacement for data layer, single map for the application
    Map<String, String> registrations = new HashMap<>();
    Map<String, Employee> registrationEmployees = new HashMap<>();
    @Override
    public boolean registerEmployeeDataForCourse(String registrationID, String courseId, Employee employee) {
        registrations.put(registrationID, courseId);
        registrationEmployees.put(registrationID, employee);
        return true;
    }
    @Override
    public boolean cancelRegistration(String registrationID) {
        registrations.remove(registrationID);
        registrationEmployees.remove(registrationID);
        return true;

    }

    @Override
    public Optional<String> getCourseId(String registrationID) {
        return Optional.ofNullable(registrations.get(registrationID));
    }

    @Override
    public Employee getEmployee(String registrationID) {
        return registrationEmployees.get(registrationID);
    }
}
