package com.Daniel.ExpenseTracker_Backend.service;
import com.Daniel.ExpenseTracker_Backend.model.Expense;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {

    public void exportToExcel(List<Expense> expenses, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Expenses");

            // Nagłówki kolumn
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Expense Date", "Expense Value", "Description", "Expense Type"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Dane wydatków
            int rowNum = 1;
            for (Expense expense : expenses) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(expense.getExpenseDate().toString());
                row.createCell(1).setCellValue(expense.getExpenseValue());
                row.createCell(2).setCellValue(expense.getDescription());
                row.createCell(3).setCellValue(expense.getExpenseType().toString());
            }

            // Zapis do pliku
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
