package com.Daniel.ExpenseTracker_Backend.controller;

import com.Daniel.ExpenseTracker_Backend.model.Expense;
import com.Daniel.ExpenseTracker_Backend.model.ExpenseType;
import com.Daniel.ExpenseTracker_Backend.service.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ExpenseController {

    @Autowired
    private ExpenseServiceImpl expenseService;

    @PostMapping("/addExpense")
    public ResponseEntity<?> newExpense(@RequestBody Expense expense){
        return new ResponseEntity<>(expenseService.saveExpense(expense), HttpStatus.CREATED);
    }

    @GetMapping("/allExpense")
    public ResponseEntity<?> getAllExpense(){
        return new ResponseEntity<>(expenseService.getAllExpense(), HttpStatus.OK);
    }

    @GetMapping("expensesByType")
    public List<Expense> getExpensesByType(@RequestParam("type") ExpenseType expenseType) {
        return expenseService.getExpensesByType(expenseType);
    }



}
