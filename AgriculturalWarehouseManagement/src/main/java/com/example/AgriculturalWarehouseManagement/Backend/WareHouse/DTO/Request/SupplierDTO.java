package com.example.AgriculturalWarehouseManagement.Backend.WareHouse.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {

    private Integer supplierID;
    private String supplierName;
    private String contactInfo;

}