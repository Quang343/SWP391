package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ProductBatchDTO
{
    private Integer batchID;
    private Integer productDetailID;
    private LocalDate manufactureDate;
    private Integer quantity;

    public ProductBatchDTO() {
    }

    public ProductBatchDTO(Integer batchID, Integer productDetailID, LocalDate manufactureDate, Integer quantity) {
        this.batchID = batchID;
        this.productDetailID = productDetailID;
        this.manufactureDate = manufactureDate;
        this.quantity = quantity;
    }

}
