package com.example.AgriculturalWarehouseManagement.Backend.services.admin;


import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CategoryExcelExporter {
    private final XSSFWorkbook workbook;
    private final Sheet sheet;
    private final List<Category> categoryList;

    public CategoryExcelExporter(List<Category> categoryList) {
        this.categoryList = categoryList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Categories");
    }

    private void writeHeaderRow() {
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        String[] headers = {"ID", "Name", "Description", "Status"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
    }

    private void writeDataRows() {
        int rowCount = 1;

        for (Category category : categoryList) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(category.getCategoryId());
            row.createCell(1).setCellValue(category.getName());
            row.createCell(2).setCellValue(category.getDescription());
            row.createCell(3).setCellValue(category.getStatus());
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
