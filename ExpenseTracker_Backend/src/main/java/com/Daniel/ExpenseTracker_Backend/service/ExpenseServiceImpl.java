package com.Daniel.ExpenseTracker_Backend.service;

import com.Daniel.ExpenseTracker_Backend.model.Expense;
import com.Daniel.ExpenseTracker_Backend.model.ExpenseType;
import com.Daniel.ExpenseTracker_Backend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    // Get expenses by type
    public List<Expense> getExpensesByType(ExpenseType expenseType) {
        return expenseRepository.findByExpenseType(expenseType);
    }

    @Override
    public Expense getExpenseById(Long id) {
        return null;
    }

    @Override
    public String deleteExpense(Long id) {
        return null;
    }

    @Override
    public Expense editExpense(Expense expense, Long id) {
        return null;
    }
}
