package com.example.AgriculturalWarehouseManagement.Backend.WareHouse.Entities;//package com.example.AgriculturalWarehouseManagement.Backend.WareHouse.Entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import java.util.Date;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//
//public class StockIn {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer stockInID;
//
//    @ManyToOne
//    @JoinColumn(name = "SupplierID", referencedColumnName = "SupplierID")
//    private Suppliers supplier;
//
//    @ManyToOne
//    @JoinColumn(name = "WarehouseID", referencedColumnName = "WarehouseID")
//    private Warehouse warehouse;
//
//    @Column(name = "StockInDate")
//    private Date stockInDate;
//
//    @Column(name = "Note")
//    private String note;
//
//    // Getters and Setters
//}