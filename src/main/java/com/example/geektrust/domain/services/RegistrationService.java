package com.example.geektrust.domain.services;

import com.example.geektrust.domain.Entities.Course;
import com.example.geektrust.domain.Entities.Employee;
import com.example.geektrust.domain.Messages;
import com.example.geektrust.domain.data.RegistrationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RegistrationData registrationData;
    @Autowired private CourseService courseService;

    public String register(String name, String email, String courseId){
        Optional<Course> courseOptional = courseService.getCourse(courseId);
        if(!courseOptional.isPresent())
            return Messages.INPUT_DATA_ERROR;
        Course course = courseOptional.get();
        if(!courseService.isCourseRegistrationOpen(courseId))
            return Messages. COURSE_FULL;
        Employee employee = new Employee(name, email);
        String registrationID = generateCourseRegistrationID(employee, course);
        registrationData.registerEmployeeDataForCourse(registrationID, course.getCourseId(), employee);
        courseService.addRegistration(course.getCourseId(), registrationID);

        LOGGER.info("Successfully registered course for {} with {}", employee.name, registrationID);
        return registrationID + " " + Messages.ACCEPTED;
    }

    public String cancelRegistration(String registrationID){
        Optional<String> courseIdOptional = registrationData.getCourseId(registrationID);
        if(!courseIdOptional.isPresent())
            return Messages.INPUT_DATA_ERROR;
        Optional<Course> courseOptional = courseService.getCourse(courseIdOptional.get());
        if(!courseOptional.isPresent())
            return Messages.INPUT_DATA_ERROR;
        Course course = courseOptional.get();
        String msg = registrationID + " ";
        if(course.isAllotted())
            return msg + Messages.CANCEL_REJECTED;
        registrationData.cancelRegistration(registrationID);
        courseService.cancelRegistration(course.getCourseId(), registrationID);
        return msg + Messages.CANCEL_ACCEPTED;
    }

    public Employee getEmployeeData(String registrationID){
        return registrationData.getEmployee(registrationID);
    }

    //REG-COURSE-<EMPLOYEENAME>-<COURSENAME>
    private String generateCourseRegistrationID(Employee employee, Course course) {
        return "REG-COURSE-" + employee.name + "-" + course.name;
    }
}
