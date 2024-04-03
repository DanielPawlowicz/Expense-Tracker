package com.Daniel.ExpenseTracker_Backend.repository;

import com.Daniel.ExpenseTracker_Backend.model.Expense;
import com.Daniel.ExpenseTracker_Backend.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // get expenses by type
    List<Expense> findByExpenseType(ExpenseType expenseType);

    // get expenses by date
    List<Expense> findByExpenseDate(LocalDate expenseDate);

    // get expenses in date range
    List<Expense> findByExpenseDateBetween(LocalDate startDate, LocalDate endDate);

    // get all expenses sorted by date (newest to oldest)
    List<Expense> findAllByOrderByExpenseDateDesc();

}
