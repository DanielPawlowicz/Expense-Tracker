package com.Daniel.ExpenseTracker_Backend.service;

import com.Daniel.ExpenseTracker_Backend.controller.exception.ExpenseNotFoundException;
import com.Daniel.ExpenseTracker_Backend.model.Expense;
import com.Daniel.ExpenseTracker_Backend.model.ExpenseType;
import com.Daniel.ExpenseTracker_Backend.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.time.temporal.ChronoUnit;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;


// BASIC CRUD
    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpense() {
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id)
                .orElseThrow(()->new ExpenseNotFoundException(id));
    }

    @Override
    public String deleteExpense(Long id) {
        if(!expenseRepository.existsById(id)){
            throw new ExpenseNotFoundException(id);
        }
        expenseRepository.deleteById(id);
        return "Expense with id " + id + " deleted successfully";
    }

    @Override
    public Expense editExpense(Expense newExpense, Long id) {
        Expense expense = expenseRepository.findById(id).get();
            expense.setExpenseDate(newExpense.getExpenseDate());
            expense.setExpenseType(newExpense.getExpenseType());
            expense.setExpenseValue(newExpense.getExpenseValue());
            expense.setDescription(newExpense.getDescription());

        return expenseRepository.save(expense);
    }



// OTHER

    // Get expenses by type  -ordered by date desc
    public List<Expense> getExpensesByType(ExpenseType expenseType) {
        return expenseRepository.findByExpenseTypeOrderByExpenseDateDesc(expenseType);
    }

    // get expenses by date
    public List<Expense> getExpensesByDate(LocalDate expenseDate) {
        return expenseRepository.findByExpenseDate(expenseDate);
    }

    // get expenses in date range  -ordered by date desc
    public List<Expense> getExpensesByDateRange(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findByExpenseDateBetweenOrderByExpenseDateDesc(startDate, endDate);
    }

    // get all expenses sorted by date (newest to oldest)
    public List<Expense> getAllByDateDesc() {
        return expenseRepository.findAllByOrderByExpenseDateDesc();
    }

    // Get expenses by specific date and type   -ordered by date desc
    public List<Expense> getExpensesByDateAndType(LocalDate date, ExpenseType type) {
        return expenseRepository.findByExpenseDateAndExpenseType(date, type);
    }

    // get expenses by date Range and specific type  -ordered by date desc
    public List<Expense> getExpensesWithinDateRangeAndType(LocalDate startDate, LocalDate endDate, ExpenseType expenseType) {
        return expenseRepository.findByExpenseDateBetweenAndExpenseTypeOrderByExpenseDateDesc(startDate, endDate, expenseType);
    }

    // Get expenses from the last X days  -ordered by date desc
    public List<Expense> getExpensesFromLastXDaysOrderedByDateDesc(int days) {
        LocalDate startDate = LocalDate.now().minus(days, ChronoUnit.DAYS);
        return expenseRepository.findByExpenseDateAfterOrderByExpenseDateDesc(startDate);
    }

    // Get expenses from the last X days and specific type  -ordered by date desc;
    public List<Expense> getExpensesFromLastXDaysAndType(int days, ExpenseType type) {
        LocalDate startDate = LocalDate.now().minus(days, ChronoUnit.DAYS);
        return expenseRepository.findByExpenseDateAfterAndExpenseType(startDate, type);
    }

    // get expenses value from this month
    public double getSummaryValueOfExpensesFromBeginningOfMonth() {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = YearMonth.from(today).atDay(1);
        List<Expense> expenses = expenseRepository.findByExpenseDateAfter(startOfMonth);
        double summaryValue = expenses.stream()
                .mapToDouble(Expense::getExpenseValue)
                .sum();
        return summaryValue;
    }

}
