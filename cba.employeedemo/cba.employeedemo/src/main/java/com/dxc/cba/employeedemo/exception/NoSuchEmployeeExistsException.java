package com.dxc.cba.employeedemo.exception;

/**
 * This class is responsible for handling scenarios and throwing custom exceptions
 * like when an employee is not available in the database. This exception class should extend RuntimeException.
 */
public class NoSuchEmployeeExistsException extends  RuntimeException{
    public NoSuchEmployeeExistsException() {
    }

    public NoSuchEmployeeExistsException(String message) {
        super(message);
    }

    public NoSuchEmployeeExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchEmployeeExistsException(Throwable cause) {
        super(cause);
    }


}
