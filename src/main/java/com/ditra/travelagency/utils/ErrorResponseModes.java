package com.ditra.travelagency.utils;

public class ErrorResponseModes {
    private String message;

    public ErrorResponseModes(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
