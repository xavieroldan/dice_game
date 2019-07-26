package com.dicegame.control.exceptions;

public class FieldVoidException extends Exception {
    public FieldVoidException(String message, Throwable cause) {
        super(message, cause);
    }
    public FieldVoidException(String message) {
        super(message);
    }
}
