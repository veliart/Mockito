package com.example.mockito.exception;

public class EmployeeStorageIsFullException extends RuntimeException {

    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}