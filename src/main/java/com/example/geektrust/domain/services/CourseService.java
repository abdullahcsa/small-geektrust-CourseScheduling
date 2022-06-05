package com.example.geektrust.domain.services;

import com.example.geektrust.domain.Entities.Course;
import com.example.geektrust.domain.Entities.Employee;
import com.example.geektrust.domain.Messages;
import com.example.geektrust.domain.data.CourseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class CourseService {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CourseData courseData;
    @Autowired
    private RegistrationService registrationService;

    private String SPACES = " ";

    public String addCourse(Course course) {
        String id = generateCourseID(course);
        course.setCourseId(id);
        courseData.persistCourse(id, course);
        LOGGER.info("Persisted Course {}", id);
        return id;
    }
    public String allotCourse(String courseId) {
        Optional<Course> courseOptional = getCourse(courseId);
        if (!courseOptional.isPresent())
            return Messages.INPUT_DATA_ERROR;
        Course course = courseOptional.get();
        String status = Messages.CANCELLED;
        if (courseData.allotCourse(courseId))
            status = Messages.CONFIRMED;
        Set<String> registrations = courseData.getRegistrations(courseId);
        StringBuilder sb = new StringBuilder();
        for (String registrationId : registrations) {
            Employee employeeData = registrationService.getEmployeeData(registrationId);
            sb  // <course-registration-id> <email-id> <course-offering-id> <course-name> <instructor> <date-in-ddmmyyyy> <status>
                    .append(registrationId).append(SPACES)
                    .append(employeeData.email).append(SPACES)
                    .append(courseId).append(SPACES)
                    .append(course.name).append(SPACES)
                    .append(course.instructor).append(SPACES)
                    .append(course.date).append(SPACES)
                    .append(status).append(System.lineSeparator());

        }
        return sb.toString();
    }


    //OFFERING-<COURSENAME>-<INSTRUCTORNAME>
    private String generateCourseID(Course course) {
        return "OFFERING-" + course.name + "-" + course.instructor;
    }

    Map<String, Course> courseOfferings = new HashMap<>();
    boolean courseExists(String offering){
        return courseOfferings.containsKey(offering);
    }

    Optional<Course> getCourse(String courseId) {
        return courseData.getCourseData(courseId);
    }

    public boolean isCourseRegistrationOpen(String courseId){
        return courseData.isRegistrationOpen(courseId);
    }

    public boolean addRegistration(String courseId, String registrationID) {
        courseData.addRegistration(courseId, registrationID);
        return true;
    }

    public boolean cancelRegistration(String courseId, String registrationID) {
        courseData.cancelRegistration(courseId, registrationID);
        return true;
    }
}