package com.book.BookCatalogue.exception;

public class NoSuchBookExistsException extends RuntimeException{
    public NoSuchBookExistsException(String message) {
        super(message);
    }

    public NoSuchBookExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchBookExistsException(Throwable cause) {
        super(cause);
    }


}

