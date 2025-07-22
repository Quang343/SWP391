package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.CheckOutProductsResponse;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.VoucherUsersResponse;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VoucherUsersService {

    @Autowired
    private VoucherRepository voucherRepository;

    // View checkout products
    public List<VoucherUsersResponse> getVoucherUsers() {
        List<Object[]> raw = voucherRepository.rawGetVouchers();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<VoucherUsersResponse> list = raw.stream().map(row -> {
            // Convert Timestamp to LocalDateTime
            Timestamp startTimestamp = (Timestamp) row[7];  // Assuming startDate is at index 7
            Timestamp endTimestamp = (Timestamp) row[3];    // Assuming endDate is at index 3

            // Convert Timestamp to LocalDateTime
            LocalDateTime startDate = startTimestamp.toLocalDateTime();
            LocalDateTime endDate = endTimestamp.toLocalDateTime();

            // Assuming isLocked is a Boolean value and not a String
            Boolean isLockedBoolean = (Boolean) row[4];

            String isLocked = isLockedBoolean != null ? isLockedBoolean.toString() : "false";

            return new VoucherUsersResponse(
                    ((Number) row[0]).intValue(),
                    (String) row[1],
                    ((Number) row[2]).doubleValue(),
                    endDate,    // Use LocalDateTime for endDate
                    isLocked,   // Now isLocked is a String, converted from Boolean
                    ((Number) row[5]).doubleValue(),
                    ((Number) row[6]).intValue(),
                    startDate,  // Use LocalDateTime for startDate
                    (String) row[8],
                    ((Number) row[9]).intValue(),
                    (String) row[10],
                    ((Number) row[11]).intValue(),
                    ((Number) row[12]).intValue()
            );
        }).toList();

        return list;
    }

    public List<VoucherUsersResponse> getVoucherUsersOrEmpty() {
        if (getVoucherUsers().isEmpty()) {
            return new ArrayList<>();
        } else {
            return getVoucherUsers();
        }
    }
}
