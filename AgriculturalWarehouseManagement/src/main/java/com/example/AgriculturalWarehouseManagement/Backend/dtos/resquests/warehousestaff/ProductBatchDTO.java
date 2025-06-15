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
    private Integer importedQuantity;
    private Integer soldQuantity;

    public ProductBatchDTO() {
    }

    public ProductBatchDTO(Integer batchID, Integer productDetailID, LocalDate manufactureDate, Integer importedQuantity,Integer soldQuantity ) {
        this.batchID = batchID;
        this.productDetailID = productDetailID;
        this.manufactureDate = manufactureDate;
        this.importedQuantity = importedQuantity;
        this.soldQuantity = soldQuantity;
    }

    public Integer getRemainingQuantity() {
        return importedQuantity - (soldQuantity != null ? soldQuantity : 0);
    }


}
