package com.example.AgriculturalWarehouseManagement.Backend.services.admin;

import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class OrderExcelExporter {
    private final XSSFWorkbook workbook;
    private final Sheet sheet;
    private final List<Order> orderList;

    public OrderExcelExporter(List<Order> orderList) {
        this.orderList = orderList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Orders");
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

        for (Order order : orderList) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(order.getId());
            row.createCell(1).setCellValue(order.getOrderCode());
            row.createCell(2).setCellValue(order.getUser().getEmail()); // hoặc .getUsername()
            row.createCell(3).setCellValue(order.getOrderDate().toString());
            row.createCell(4).setCellValue(order.getStatus());
            row.createCell(5).setCellValue(order.getFullName());
            row.createCell(6).setCellValue(order.getPhone());
            row.createCell(7).setCellValue(order.getAddress());
            row.createCell(8).setCellValue(order.getVoucherCode() != null ? order.getVoucherCode() : "");
            row.createCell(9).setCellValue(order.getDiscountAmount() != null ? order.getDiscountAmount().doubleValue() : 0.0);
            row.createCell(10).setCellValue(order.getTotalAmount().doubleValue());
            row.createCell(11).setCellValue(order.getFinalAmount().doubleValue());
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
