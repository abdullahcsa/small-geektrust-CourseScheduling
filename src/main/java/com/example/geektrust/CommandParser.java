package com.example.geektrust;

import com.example.geektrust.commandLineInputs.executors.Executors.*;
import com.example.geektrust.commandLineInputs.ValidatorOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static com.example.geektrust.Constants.INPUT_DATA_ERROR;

public enum CommandParser {
    ADD_COURSE_OFFERING("ADD-COURSE-OFFERING", new AddCourseWrapper()),
    REGISTER("REGISTER", new RegisterCourse()),
    ALLOT("ALLOT", new AllotCourse()),
    CANCEL("CANCEL", new CancelCourse()),
    ;

    static Logger LOGGER = LoggerFactory.getLogger(CommandParser.class);
    private  CommandWrapper commandWrapper;
    String commandName;

    CommandParser(String commandName, CommandWrapper command) {
        this.commandName = commandName;
      this.commandWrapper = command;
    }
    static Map<String, CommandParser> AllCommands;
    static {
        AllCommands = new HashMap<>();
        for (CommandParser value : values()) {
            AllCommands.put(value.commandName, value);
        }
    }

    public static String parse(String commandName, String inputParams) {
        CommandParser parser = AllCommands.get(commandName);
        LOGGER.info("Parsing command {}", commandName);
       if(null == parser)
       {
           LOGGER.error("Invalid commad {}", commandName);
           return INPUT_DATA_ERROR;
       }
        return parser.executeCommand(inputParams);
    }

    private String executeCommand(String inputParams) {

        String[] input = inputParams.split("\\s");
        commandWrapper.setInputArgs(input);
        ValidatorOutput validate = commandWrapper.validate();
        if(!validate.result)
            return validate.message;
        commandWrapper.map();
        return commandWrapper.execute();
    }
}
