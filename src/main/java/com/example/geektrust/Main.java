package com.example.geektrust;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@ComponentScan(basePackages = "com.example.geektrust")
@SpringBootApplication
public class Main implements CommandLineRunner {
    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.debug("Starting Application.. ");
        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("App starts...");
        List<String> inputCommands = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileInputStream(args[0]))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.startsWith("/*") || line.startsWith("//")) continue;
                inputCommands.add(line);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("Invalid File Name " + e);
            throw new RuntimeException(Constants.INPUT_DATA_ERROR);
        } catch (IOException e) {
            LOGGER.error("Unable to read input file" + e);
            throw new RuntimeException(Constants.INPUT_DATA_ERROR);
        }

        for (String inputCommand : inputCommands) {
            String[] splitData;
            try {
                splitData = inputCommand.split("\\s", 2);
            } catch (Exception e) {
                LOGGER.error("Error splitting input params , {}", e);
                throw new RuntimeException(Constants.INPUT_DATA_ERROR);
            }
            if (splitData.length != 2)
                throw new RuntimeException(Constants.INPUT_DATA_ERROR);
            String commandName = splitData[0];
            String inputParams = splitData[1];

            System.out.println(CommandParser.parse(commandName, inputParams));
        }

    }
}