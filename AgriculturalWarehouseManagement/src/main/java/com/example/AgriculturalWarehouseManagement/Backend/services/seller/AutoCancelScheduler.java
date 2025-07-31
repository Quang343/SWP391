package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoCancelScheduler {

    @Autowired
    private SellerApplicationService autoCancelService;

    // Chạy mỗi ngày lúc 2:00 sáng
    @Scheduled(cron = "0 * * * * ?")
    public void runAutoCancel() {
        autoCancelService.autoCancelApplications();
    }
}
