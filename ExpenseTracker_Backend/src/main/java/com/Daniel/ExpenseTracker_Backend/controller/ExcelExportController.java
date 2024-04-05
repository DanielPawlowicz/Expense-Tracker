package com.Daniel.ExpenseTracker_Backend.controller;

import com.Daniel.ExpenseTracker_Backend.model.Expense;
import com.Daniel.ExpenseTracker_Backend.service.ExcelExportService;
import com.Daniel.ExpenseTracker_Backend.service.ExpenseService;
import com.Daniel.ExpenseTracker_Backend.service.ExpenseServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ExcelExportController {

    private final ExpenseServiceImpl expenseService;
    private final ExcelExportService excelExportService;

    @Autowired
    public ExcelExportController(ExpenseServiceImpl expenseService, ExcelExportService excelExportService) {
        this.expenseService = expenseService;
        this.excelExportService = excelExportService;
    }

    // create excel file for all
//    @GetMapping("/exportToExcelAll")
//    public ResponseEntity<byte[]> exportToExcelAll() {
//        List<Expense> expenses = expenseService.getAllByDateDesc();
//        byte[] excelData = excelExportService.exportToExcel(expenses, "expenses_All.xlsx"); // Modify exportToExcel method to return byte[] directly
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=expenses.xlsx");
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//                .body(excelData);
//    }

    // create excel file from this month
//    @GetMapping("/exportToExcelThisMonth")
//    public ResponseEntity<String> exportToExcelThisMonth() {
//        List<Expense> expenses = expenseService.getExpensesFromBeginningOfMonth();
//        excelExportService.exportToExcel(expenses, "expenses.xlsx");
//        return new ResponseEntity<>("Excel exported successfully", HttpStatus.OK);
//    }

    @GetMapping("/exportFromSpecificMonth")
    public ResponseEntity<Object> exportToExcel(@RequestParam("year") int year, @RequestParam("month") int month) throws IOException {
        List<Expense> expenses = expenseService.getExpensesByMonthAndYear(year, month);
        excelExportService.exportToExcel(expenses, "expenses_" + year + "_" + month + ".xlsx");

        String filePath = "expenses_" + year + "_" + month + ".xlsx"; // Ścieżka do wygenerowanego pliku Excel
        Path path = Paths.get(filePath);
        byte[] excelData = Files.readAllBytes(path);

        ByteArrayResource resource = new ByteArrayResource(excelData);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=expenses_" + year + "_" + month + ".xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .contentLength(excelData.length)
                .body(resource);
    }

//    @GetMapping("/export")
//    public ResponseEntity<Object> exportToExcel() throws IOException {
//        String filePath = "expenses.xlsx"; // Ścieżka do wygenerowanego pliku Excel
//        Path path = Paths.get(filePath);
//        byte[] excelData = Files.readAllBytes(path);
//
//        ByteArrayResource resource = new ByteArrayResource(excelData);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=expenses.xlsx");
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//                .contentLength(excelData.length)
//                .body(resource);
//    }
}

