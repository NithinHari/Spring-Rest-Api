package com.book.BookCatalogue.exception;

public class CustomBookException extends RuntimeException {
    public CustomBookException (String message) {
        super(message);
    }
    public CustomBookException(String message, Throwable cause){
        super(message,cause);
    }
    public CustomBookException(Throwable cause) {
        super(cause);
    }
}
