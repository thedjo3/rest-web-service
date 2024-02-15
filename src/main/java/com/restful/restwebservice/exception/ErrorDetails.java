package com.restful.restwebservice.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

    // The time when error is happened.
    private LocalDateTime timestamp;

    // Message of the error.
    private String message;

    // Details of the error.
    private String details;


    // Constructor
    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
