package com.example.geektrust.domain;

import com.example.geektrust.domain.Entities.Course;
import com.example.geektrust.domain.inputlayer.mapperobjects.CourseMapper;
import com.example.geektrust.domain.inputlayer.mapperobjects.RegisterCourseMapper;
import com.example.geektrust.domain.services.CourseService;
import com.example.geektrust.domain.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServiceRegistry {

    public String addCourse(CourseMapper mapperObject) {
        return courseService.addCourse(new Course(mapperObject));
    }

    public String registerCourse(RegisterCourseMapper mapper){
        return registrationService.register(mapper.name, mapper.email, mapper.courseId);
    }

    public String allotCourse(String courseId) {
        return courseService.allotCourse(courseId);
    }

    public String cancelRegistration(String registrationId){
        return registrationService.cancelRegistration(registrationId);
    }

    @Autowired
    RegistrationService registrationService;
    @Autowired
    CourseService courseService;
    @Autowired
    private ServiceRegistry registry;

    public static ServiceRegistry serviceRegistry() {
        return serviceRegistry;
    }

    public  static ServiceRegistry serviceRegistry ;

    @PostConstruct
    void init(){
        serviceRegistry = this;
    }

}