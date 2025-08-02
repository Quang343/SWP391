package com.example.AgriculturalWarehouseManagement.Backend.services.user;

import com.example.AgriculturalWarehouseManagement.Backend.models.Cart;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartCleanupService {

    @Autowired
    private CartRepository cartRepository;

    @Scheduled(fixedRate = 10 * 1000) // mỗi 1 phút
    public void removeExpiredCarts() {
        LocalDateTime oneHourAgo = LocalDateTime.now().minusHours(1);
        List<Cart> expiredCarts = cartRepository.findByCreatedAtBefore(oneHourAgo);

        if (!expiredCarts.isEmpty()) {
            cartRepository.deleteAll(expiredCarts);
            System.out.println("Đã xóa " + expiredCarts.size() + " cart hết hạn.");
        }
    }

}
