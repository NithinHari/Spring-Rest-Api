package com.dxc.cba.employeedemo.exception;

/**
 * This class is responsible for handling scenarios and throwing custom exceptions
 * like when an employee is not available in the database or
 *  some validation exception for mandatory fields.
 *  This exception class should extend RuntimeException.
 */
public class CustomEmployeeException extends  RuntimeException{
    public CustomEmployeeException() {
    }

    public CustomEmployeeException(String message) {
        super(message);
    }

    public CustomEmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomEmployeeException(Throwable cause) {
        super(cause);
    }


}
