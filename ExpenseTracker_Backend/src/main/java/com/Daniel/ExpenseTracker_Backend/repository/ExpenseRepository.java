package com.Daniel.ExpenseTracker_Backend.repository;

import com.Daniel.ExpenseTracker_Backend.model.Expense;
import com.Daniel.ExpenseTracker_Backend.model.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // get expenses by type  -ordered by date desc
    List<Expense> findByExpenseTypeOrderByExpenseDateDesc(ExpenseType expenseType);

    // get expenses by date
    List<Expense> findByExpenseDate(LocalDate expenseDate);

    // get expenses in date range  -ordered by date desc
    List<Expense> findByExpenseDateBetweenOrderByExpenseDateDesc(LocalDate startDate, LocalDate endDate);

    // get all expenses sorted by date (newest to oldest)
    List<Expense> findAllByOrderByExpenseDateDesc();

    // Get expenses by specific date and type ordered by date desc
    @Query("SELECT e FROM Expense e WHERE e.expenseDate = ?1 AND e.expenseType = ?2 ORDER BY e.expenseDate DESC")
    List<Expense> findByExpenseDateAndExpenseType(LocalDate expenseDate, ExpenseType expenseType);

    // get expenses by date Range and specific type  -ordered by date desc
    List<Expense> findByExpenseDateBetweenAndExpenseTypeOrderByExpenseDateDesc(LocalDate startDate, LocalDate endDate, ExpenseType expenseType);

    // Get expenses from the last X days  -ordered by date desc
    List<Expense> findByExpenseDateAfterOrderByExpenseDateDesc(LocalDate startDate);

    // Get expenses from the last X days and specific type  -ordered by date desc;
    List<Expense> findByExpenseDateAfterAndExpenseType(LocalDate startDate, ExpenseType expenseType);

    // Get expenses after a specific date (this month)
    List<Expense> findByExpenseDateAfter(LocalDate date);

    // get the value from specific month
    List<Expense> findByExpenseDateBetween(LocalDate startOfMonth, LocalDate endOfMonth);
}
