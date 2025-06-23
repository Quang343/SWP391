package com.example.AgriculturalWarehouseManagement;

import com.example.AgriculturalWarehouseManagement.models.VoucherStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class AgriculturalWarehouseManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgriculturalWarehouseManagementApplication.class, args);
//		System.out.println(Paths.get("uploads").toAbsolutePath());
	}

}
