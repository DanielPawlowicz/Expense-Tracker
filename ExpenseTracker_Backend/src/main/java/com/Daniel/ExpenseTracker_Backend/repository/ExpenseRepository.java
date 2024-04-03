package com.Daniel.ExpenseTracker_Backend.repository;

import com.Daniel.ExpenseTracker_Backend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
