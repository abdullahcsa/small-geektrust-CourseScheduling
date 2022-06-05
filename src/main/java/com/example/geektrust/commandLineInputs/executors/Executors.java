package com.example.geektrust.commandLineInputs.executors;

import com.example.geektrust.commandLineInputs.ValidatorOutput;
import com.example.geektrust.domain.ServiceRegistry;
import com.example.geektrust.domain.constants.Messages;
import com.example.geektrust.domain.inputlayer.mapperobjects.CourseMapper;
import com.example.geektrust.domain.inputlayer.mapperobjects.RegisterCourseMapper;
import com.example.geektrust.dto.CourseDTO;
import com.example.geektrust.dto.RegistrantEntry;

public class Executors {
    public interface CommandWrapper {
        public String execute();

        default void map() {
        }

        ;

        public ValidatorOutput validate();

        void setInputArgs(String[] input);
    }

    public static abstract class BaseCommandWrapper implements CommandWrapper {
        String[] input;
        ServiceRegistry serviceRegistry = ServiceRegistry.serviceRegistry();
        @Override
        public void setInputArgs(String[] input){
            this.input = input;
        }
        public BaseCommandWrapper(String[] args) {
            input = args;
        }

        public BaseCommandWrapper() {
            
        }
    }

    public static class AddCourseWrapper extends BaseCommandWrapper {
        CourseDTO courseDTO;
        CourseMapper courseMapper;

        public AddCourseWrapper(String[] args) {
            super(args);
        }

        public AddCourseWrapper() {
            super();
        }

        @Override
        public ValidatorOutput validate() {
            if (input.length != 5) return new ValidatorOutput(Messages.ErrorMessages.MISSING_INPUT.getMsg());
            courseDTO = new CourseDTO();
            courseDTO.setName(input[0]);
            courseDTO.setInstructor(input[1]);
            courseDTO.setDate(input[2]);
            try {
                courseDTO.setMin(Integer.parseInt(input[3]));
                courseDTO.max = Integer.parseInt(input[4]);
            } catch (Exception e) {
                return new ValidatorOutput(Messages.ErrorMessages.INPUT_DATA_ERROR.getMsg());
            }

            return new ValidatorOutput(true);
        }

        @Override
        public void map() {
            courseMapper = new CourseMapper();
            courseMapper.setName(courseDTO.getName());
            courseMapper.setInstructor(courseDTO.getInstructor());
            courseMapper.setDate(courseDTO.getDate());
            courseMapper.setMin(courseDTO.getMin());
            courseMapper.setMax(courseDTO.max);
        }

        @Override
        public String execute() {
            return serviceRegistry.addCourse(courseMapper);
        }
    }



    public static class RegisterCourse extends BaseCommandWrapper {
        RegisterCourseMapper mapper;
            RegistrantEntry registrantEntry;

        public ValidatorOutput validate() {
            if(input.length != 2 || !validEmail(input[0]))
                return new ValidatorOutput(Messages.ErrorMessages.INPUT_DATA_ERROR.getMsg());
            registrantEntry = new RegistrantEntry();
            registrantEntry.setEmailAddress(input[0]);
            registrantEntry.setCourseOffering(input[1]);

            return new ValidatorOutput(true);
        }

        private boolean validEmail(String email) {
            return email.contains("@");
        }

        @Override
        public void map() {
            mapper = new RegisterCourseMapper();

            mapper.setEmail(registrantEntry.getEmailAddress());
            mapper.setName(mapper.getEmail().split("@")[0]);
            mapper.setCourseId(registrantEntry.getCourseOffering());
        }

        @Override
        public String execute() {
            return serviceRegistry.registerCourse(mapper);
        }
    }

    public static class AllotCourse extends  BaseCommandWrapper{
       String courseId;
        @Override
        public ValidatorOutput validate() {
            if(input.length != 1)
                return new ValidatorOutput(Messages.ErrorMessages.INPUT_DATA_ERROR.getMsg());

            this.courseId = input[0];
            return new ValidatorOutput(true);
        }
        @Override
        public String execute(){
            return serviceRegistry.allotCourse(courseId);
        }
    }

    public static class CancelCourse extends BaseCommandWrapper {
        private String registrationId;

        @Override
        public ValidatorOutput validate() {
            if (input.length != 1)
                return new ValidatorOutput(Messages.ErrorMessages.INPUT_DATA_ERROR.getMsg());

            this.registrationId = input[0];
            return new ValidatorOutput(true);
        }
        @Override
        public String execute(){
            return serviceRegistry.cancelRegistration(registrationId);
        }
    }
}
