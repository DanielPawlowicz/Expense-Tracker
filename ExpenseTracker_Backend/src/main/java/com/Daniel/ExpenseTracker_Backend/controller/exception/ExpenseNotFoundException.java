package com.Daniel.ExpenseTracker_Backend.controller.exception;

public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(Long id) {
        super("Could not find the task with id: "+id);
    }
}
