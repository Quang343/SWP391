package com.example.AgriculturalWarehouseManagement.Backend.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryid")
    private Long id;

    @Column(name = "categoryname", length = 100, nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(length = 100)
    private String status;

}