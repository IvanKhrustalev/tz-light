package com.example.tzlight.exception;

public class NotAdminException extends RuntimeException {
    private String message;
    public NotAdminException(String message) {
        this.message = message;
    }
}
