package com.example.AgriculturalWarehouseManagement.Backend.controllers.warehousestaff;


import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.ProductBatchDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDTO;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff.StockInDetailDTO;
import com.example.AgriculturalWarehouseManagement.Backend.models.StockIn;
import com.example.AgriculturalWarehouseManagement.Backend.models.Suppliers;
import com.example.AgriculturalWarehouseManagement.Backend.models.Warehouse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.StockInRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.SupplierRepository;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.WarehouseRepository;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockInDetailService;
import com.example.AgriculturalWarehouseManagement.Backend.services.warehousestaff.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/stockins")
public class StockInController {
    @Autowired
    private StockInService stockInService;

    @Autowired
    private StockInDetailService stockInDetailService; // Thêm để lấy chi tiết

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private StockInRepository stockInRepository;

    @PostMapping
    public ResponseEntity<StockInDTO> createStockIn(@RequestBody StockInDTO stockInDTO) {
        StockInDTO createdStockIn = stockInService.createStockIn(stockInDTO);
        return ResponseEntity.ok(createdStockIn);
    }

    // API endpoint for paginated StockIn
    @GetMapping("/paginatedStockIn")
    @ResponseBody
    public Page<StockIn> getPaginatedStockIn(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return stockInService.getPaginatedStockIn(page, size);
    }

    // Thymeleaf view for paginated StockIn
    @GetMapping("/warehouse/stockin")
    public String listStockIn(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<StockIn> stockInPage = stockInService.getPaginatedStockIn(page, size);
        model.addAttribute("stockIns", stockInPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", stockInPage.getTotalPages());
        model.addAttribute("totalItems", stockInPage.getTotalElements());
        return "BackEnd/WareHouse/stockin";
    }

    @GetMapping
    public ResponseEntity<List<StockInDTO>> getAllStockIns() {
        List<StockInDTO> stockIns = stockInService.getAllStockIns();
        return ResponseEntity.ok(stockIns);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockInDTO> getStockInById(@PathVariable Integer id) {
        StockInDTO stockInDTO = stockInService.getStockInById(id);
        return ResponseEntity.ok(stockInDTO);
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<List<StockInDetailDTO>> getStockInDetailsByStockInId(@PathVariable Integer id) {
        List<StockInDetailDTO> stockInDetails = stockInDetailService.getStockInDetailsByStockInId(id);
        return ResponseEntity.ok(stockInDetails);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockIn(@PathVariable Integer id) {
        stockInService.deleteStockIn(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockInDTO> updateStockIn(@PathVariable Integer id, @RequestBody StockInDTO stockInDTO) {
        try {
            StockInDTO updatedStockIn = stockInService.updateStockIn(id, stockInDTO);
            return updatedStockIn != null ? ResponseEntity.ok(updatedStockIn) : ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(null); // Xử lý ngoại lệ từ service
        }
    }


    @PostMapping("/with-details")
    public ResponseEntity<StockIn> createStockInWithDetails(
            @RequestPart("stockIn") StockInDTO stockInDTO,
            @RequestPart("details") List<StockInDetailDTO> details,
            @RequestPart(value = "newBatches", required = false) List<ProductBatchDTO> newBatches) {
        try {
            if (stockInDTO.getSupplierID() == null || stockInDTO.getWarehouseID() == null || stockInDTO.getStockInDate() == null) {
                return ResponseEntity.badRequest().body(null);
            }

            StockIn stockIn = new StockIn();
            Suppliers supplier = supplierRepository.findById(stockInDTO.getSupplierID())
                    .orElseThrow(() -> new IllegalArgumentException("Supplier ID " + stockInDTO.getSupplierID() + " không tồn tại."));
            Warehouse warehouse = warehouseRepository.findById(stockInDTO.getWarehouseID())
                    .orElseThrow(() -> new IllegalArgumentException("Warehouse ID " + stockInDTO.getWarehouseID() + " không tồn tại."));
            stockIn.setSupplierID(supplier);
            stockIn.setWarehouseID(warehouse);
            stockIn.setStockInDate(stockInDTO.getStockInDate());
            stockIn.setNote(stockInDTO.getNote());

            StockIn savedStockIn = stockInService.saveStockInWithDetailsUsingOldApis(stockIn, details, newBatches);
            return ResponseEntity.ok(savedStockIn);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }
}
