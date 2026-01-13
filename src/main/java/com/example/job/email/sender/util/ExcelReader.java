package com.example.job.email.sender.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelReader {

    public List<String> readEmails(String excelPath) {

        File file = new File(excelPath);

        if (!file.exists()) {
            throw new IllegalStateException(
                    "Excel file not found at path: " + excelPath
            );
        }

        List<String> emails = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    String email = cell.getStringCellValue().trim();
                    if (!email.isEmpty()) {
                        emails.add(email);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel file", e);
        }

        return emails;
    }
}
