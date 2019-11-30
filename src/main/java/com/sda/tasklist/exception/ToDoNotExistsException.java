package com.sda.tasklist.exception;

public class ToDoNotExistsException extends Exception {
    public ToDoNotExistsException(String message) {
        super(message);
    }
}
