package com.example.geektrust.commandLineInputs;


public class ValidatorOutput {
    public boolean result = true;
    public String message;

    public ValidatorOutput(String error) {
        this.result = false;
        this.message = error;
    }

    public ValidatorOutput(boolean result) {
        this.result = true;
    }
}
