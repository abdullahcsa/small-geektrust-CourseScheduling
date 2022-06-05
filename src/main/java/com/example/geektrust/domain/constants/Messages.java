package com.example.geektrust.domain.constants;
public class Messages {
    public enum ErrorMessages {
        MISSING_INPUT("Missing input"),
        INPUT_DATA_ERROR("INPUT_DATA_ERROR "),
        ;

        public String getMsg() {
            return msg;
        }

        String msg;
        String cause;

        ErrorMessages(String msg) {
            this.msg = msg;
        }
    }
}
