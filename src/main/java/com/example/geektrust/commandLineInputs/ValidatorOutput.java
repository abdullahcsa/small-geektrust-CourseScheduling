package com.example.geektrust.commandLineInputs;


public class ValidatorOutput {
    private boolean result = true;
    private String message;

    public ValidatorOutput(String error) {
        this.setResult(false);
        this.setMessage(error);
    }

    public ValidatorOutput(boolean result) {
        this.setResult(true);
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
