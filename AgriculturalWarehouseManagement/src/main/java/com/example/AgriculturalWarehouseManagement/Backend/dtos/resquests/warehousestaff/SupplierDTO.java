package com.example.AgriculturalWarehouseManagement.Backend.dtos.resquests.warehousestaff;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SupplierDTO {

    private Integer supplierID;
    private String supplierName;
    private String contactInfo;
    private String logo;

    public SupplierDTO() {
    }

    public SupplierDTO(Integer supplierID, String supplierName, String contactInfo, String logo) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.contactInfo = contactInfo;
        this.logo = logo;
    }

    public SupplierDTO(String s) {
    }


}