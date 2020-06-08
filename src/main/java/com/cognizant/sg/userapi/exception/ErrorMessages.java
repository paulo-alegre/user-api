package com.cognizant.sg.userapi.exception;

/**
 * Error Messages
 *
 * @author Adrian Alegre
 */
public enum ErrorMessages {
    INTERNAL_SERVER_ERROR("500"),
    NOT_FOUND("404"),
    OK("200");

    private String statusCode;

    ErrorMessages(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
