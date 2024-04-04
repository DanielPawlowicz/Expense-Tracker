package com.Daniel.ExpenseTracker_Backend.controller;

import com.Daniel.ExpenseTracker_Backend.model.Expense;
import com.Daniel.ExpenseTracker_Backend.model.ExpenseType;
import com.Daniel.ExpenseTracker_Backend.service.ExpenseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ExpenseController {

    @Autowired
    private ExpenseServiceImpl expenseService;


// BASIC CRUD

    // add new expense
    @PostMapping("/addExpense")
    public ResponseEntity<?> newExpense(@RequestBody Expense expense){
        return new ResponseEntity<>(expenseService.saveExpense(expense), HttpStatus.CREATED);
    }

    // get all expenses
    @GetMapping("/allExpenses")
    public ResponseEntity<?> getAllExpense(){
        return new ResponseEntity<>(expenseService.getAllExpense(), HttpStatus.OK);
    }

    // get single expense
    @GetMapping("expense/{id}")
    public ResponseEntity<?> getExpensesByType(@PathVariable Long id) {
        return new ResponseEntity<>(expenseService.getExpenseById(id), HttpStatus.OK);
    }

    // delete expense
    @DeleteMapping("deleteExpense/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id){
        return new ResponseEntity<>(expenseService.deleteExpense(id), HttpStatus.OK);
    }

    // edit expense
    @PutMapping("editExpense/{id}")
    public ResponseEntity<?> editExpense(@RequestBody Expense newExpense, @PathVariable Long id){
        return new ResponseEntity<>(expenseService.editExpense(newExpense, id), HttpStatus.OK);
    }



// OTHER

    // get expenses by type  -ordered by date desc
    @GetMapping("expensesByTypeOrdered")
    public List<Expense> getExpensesByType(@RequestParam("type") ExpenseType expenseType) {
        return expenseService.getExpensesByType(expenseType);
    }

    // get expenses by date
    @GetMapping("/expensesByDateOrdered")
    public List<Expense> getExpensesByDate(@RequestParam("date") String date) {
        LocalDate expenseDate = LocalDate.parse(date);
        return expenseService.getExpensesByDate(expenseDate);
    }

    // get expenses in date range  -ordered by date desc
    @GetMapping("/expensesByDateRange")
    public List<Expense> getExpensesByDateRange(@RequestParam("startDate") String startDate,
                                                @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return expenseService.getExpensesByDateRange(start, end);
    }

    // get all expenses sorted by date (newest to oldest)
    @GetMapping("/allExpensesOrdered")
    public List<Expense> getAllExpensesByDate() {
        return expenseService.getAllByDateDesc();
    }

    // Get expenses by specific date and type   -ordered by date desc
    @GetMapping("/expensesByDateAndTypeOrdered")
    public ResponseEntity<List<Expense>> getExpensesByDateAndType(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("type") ExpenseType type) {
        List<Expense> expenses = expenseService.getExpensesByDateAndType(date, type);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // get expenses by date Range and specific type  -ordered by date desc
    @GetMapping("/expensesByDateRangeAndTypeOrdered")
    public ResponseEntity<List<Expense>> getExpensesWithinDateRangeAndType(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("type") ExpenseType type) {
        List<Expense> expenses = expenseService.getExpensesWithinDateRangeAndType(startDate, endDate, type);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // Get expenses from the last X days  -ordered by date desc
    @GetMapping("/expensesFromLastXDays")
    public ResponseEntity<List<Expense>> getExpensesFromLastXDaysOrderedByDateDesc(
            @RequestParam("days") int days) {
        List<Expense> expenses = expenseService.getExpensesFromLastXDaysOrderedByDateDesc(days);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // Get expenses from the last X days and specific type  -ordered by date desc;
    @GetMapping("/expensesFromLastXDaysAndType")
    public ResponseEntity<List<Expense>> getExpensesFromLastXDaysAndType(
            @RequestParam("days") int days,
            @RequestParam("type") ExpenseType type) {
        List<Expense> expenses = expenseService.getExpensesFromLastXDaysAndType(days, type);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    // get expenses value from this month
    @GetMapping("/expensesThisMonthSummary")
    public ResponseEntity<Double> getSummaryValueOfExpensesFromBeginningOfMonth() {
        double summaryValue = expenseService.getSummaryValueOfExpensesFromBeginningOfMonth();
        return new ResponseEntity<>(summaryValue, HttpStatus.OK);
    }

    // get the value from specific month
    @GetMapping("/expensesSpecificMonthSummary")
    public ResponseEntity<Double> getSummaryValueOfExpensesFromBeginningOfMonth(
            @RequestParam("month") int month,
            @RequestParam("year") int year) {
        double summaryValue = expenseService.getSummaryValueOfExpensesFromBeginningOfMonth(month, year);
        return new ResponseEntity<>(summaryValue, HttpStatus.OK);
    }

    // get expenses from this month
    @GetMapping("/expensesFromThisMonth")
    public ResponseEntity<List<Expense>> getExpensesFromCurrentMonth() {
        List<Expense> expenses = expenseService.getExpensesFromBeginningOfMonth();
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

}
