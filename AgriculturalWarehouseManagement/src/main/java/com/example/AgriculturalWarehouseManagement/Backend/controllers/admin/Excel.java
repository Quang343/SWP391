package com.example.AgriculturalWarehouseManagement.Backend.controllers.admin;

import com.example.AgriculturalWarehouseManagement.Backend.models.Category;
import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.Voucher;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.CategoryRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.OrderRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.ProductRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.VoucherRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.CategoryExcelExporter;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.OrderExcelExporter;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.ProductExcelExporter;
import com.example.AgriculturalWarehouseManagement.Backend.services.admin.VoucherExcelExporter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class Excel {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OrderRepository orderRepository;
    private final VoucherRepository voucherRepository;


    @GetMapping("/admin/products/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=products.xlsx";
        response.setHeader(headerKey, headerValue);

        List<Product> listProducts = productRepository.findAll();
        ProductExcelExporter exporter = new ProductExcelExporter(listProducts);
        exporter.export(response);
    }

    @GetMapping("/admin/categories/export")
    public void exportCategoriesToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=categories.xlsx");

        List<Category> listCategories = categoryRepository.findAll();
        CategoryExcelExporter exporter = new CategoryExcelExporter(listCategories);
        exporter.export(response);
    }

    @GetMapping("/admin/orders/export")
    public void exportOrdersToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=orders.xlsx");

        List<Order> orders = orderRepository.findAll();
        OrderExcelExporter exporter = new OrderExcelExporter(orders);
        exporter.export(response);
    }

    @GetMapping("/admin/vouchers/export")
    public void exportVouchersToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=Vouchers.xlsx");

        List<Voucher> vouchers = voucherRepository.findAll();
        VoucherExcelExporter exporter = new VoucherExcelExporter(vouchers);
        exporter.export(response);
    }

}
