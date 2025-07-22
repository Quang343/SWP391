package com.example.AgriculturalWarehouseManagement.Backend.services.admin;

import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import java.io.IOException;
import java.util.List;

public class ProductExcelExporter {
    private final List<Product> productList;
    private final XSSFWorkbook workbook;
    private final Sheet sheet;

    public ProductExcelExporter(List<Product> productList) {
        this.productList = productList;
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("Products");
    }

    private void writeHeaderRow() {
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        String[] headers = {"ID", "Name", "Category", "Description", "Status"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
    }

    private void writeDataRows() {
        int rowCount = 1;
        for (Product product : productList) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(product.getId());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getCategory().getName());
            row.createCell(3).setCellValue(product.getDescription());
            row.createCell(4).setCellValue(product.getStatus().name());
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
