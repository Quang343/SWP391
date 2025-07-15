package com.example.AgriculturalWarehouseManagement.Backend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private int roleID;

    @Column(name = "rolename", nullable = false, length = 100)
    private String roleName;

    @Column(length = 500)
    private String description;

    @Column(length = 20)
    private String status;
}
