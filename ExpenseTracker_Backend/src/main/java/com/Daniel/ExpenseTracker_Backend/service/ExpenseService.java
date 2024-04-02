package com.Daniel.ExpenseTracker_Backend.service;

import com.Daniel.ExpenseTracker_Backend.model.Expense;

import java.util.List;

public interface ExpenseService {

    public Expense saveExpense(Expense expense);
    public List<Expense> getAllExpense();
    public Expense getExpenseById(Long id);
    public String deleteExpense(Long id);
    public Expense editExpense(Expense expense, Long id);
}
