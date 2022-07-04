package com.dxc.cba.employeedemo.exception;

/**
 * This class handles all custom exceptions defined on CustomEmployeeException
 * and all types of exceptions like 403, 500 and so on.
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @ControllerAdvice :This annotation helps to integrate multiple exception handlers into a single global unit.
 * is something similar to an interceptor or a filter.
 * It Pre-processes the request to the controller and Post-process the response to handle exceptions.
 *
 * The @ExceptionHandler is an annotation used to handle the specific exceptions and sending the custom
 * responses to the client.
 * Define a class that extends the RuntimeException class.
 */
@ControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomEmployeeException.class)
    public ResponseEntity<ErrorResponse> handleException(CustomEmployeeException customEx){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(customEx.getMessage());
        errorResponse.setTimeStamp(new Date(System.currentTimeMillis()));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchEmployeeExistsException.class)
    public ResponseEntity<ErrorResponse> handleException(NoSuchEmployeeExistsException customEx){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(customEx.getMessage());
        errorResponse.setTimeStamp(new Date(System.currentTimeMillis()));
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(new Date(System.currentTimeMillis()));
        return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
