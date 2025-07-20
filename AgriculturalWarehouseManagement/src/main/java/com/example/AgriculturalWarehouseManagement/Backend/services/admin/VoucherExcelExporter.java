package com.example.AgriculturalWarehouseManagement.Backend.services.admin;

import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.Voucher;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VoucherExcelExporter {
    private final List<Voucher> voucherList;
    private final XSSFWorkbook workbook;
    private final Sheet sheet;

    public VoucherExcelExporter(List<Voucher> voucherList) {
        this.voucherList = voucherList;
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("Vouchers");
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
        for (Voucher voucher : voucherList) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(voucher.getId());
            row.createCell(1).setCellValue(voucher.getVoucherCode());
            row.createCell(2).setCellValue(formatDate(voucher.getStartDate()));
            row.createCell(3).setCellValue(formatDate(voucher.getEndDate()));
            row.createCell(4).setCellValue(voucher.getDiscountType().toString());
            row.createCell(5).setCellValue(voucher.getDiscountValueString());
            row.createCell(6).setCellValue(voucher.getQuantity());
            row.createCell(7).setCellValue(voucher.getUsedQuantity());
            row.createCell(8).setCellValue(voucher.getStatus().toString());
            row.createCell(9).setCellValue(voucher.getMinOrderAmountString());
            row.createCell(10).setCellValue(voucher.getIsLocked() ? "TRUE" : "FALSE");
        }

    }

    private String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(formatter);
    }


    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
