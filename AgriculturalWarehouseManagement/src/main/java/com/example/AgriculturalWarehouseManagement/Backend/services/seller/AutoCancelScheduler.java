package com.example.AgriculturalWarehouseManagement.Backend.services.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoCancelScheduler {

    @Autowired
    private SellerApplicationService autoCancelService;

    @Autowired
    private SellerApplicationService sellerApplicationService;

    // Chạy mỗi ngày lúc 2:00 sáng
    @Scheduled(cron = "0 * * * * ?")
    public void runAutoCancel() {
        autoCancelService.autoCancelApplications();
    }

    @Scheduled(cron = "0 * * * * ?") // chạy mỗi ngày lúc 2h sáng
    public void scheduleSellerDowngrade() {
        sellerApplicationService.autoDowngradeExpiredSellers();
    }

}
