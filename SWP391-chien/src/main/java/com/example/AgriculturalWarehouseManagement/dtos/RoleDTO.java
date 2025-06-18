package com.example.AgriculturalWarehouseManagement.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @NotBlank(message = "Role name is required!")
    private String name;

    private String description;

    private String status;
}
