package com.example.AgriculturalWarehouseManagement.Backend.repositorys.seller;


import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface User_SellerRepository extends JpaRepository<User, Long> {

    // 🔸 Nếu bạn cần tìm theo username (email đăng nhập), có thể thêm:
    User findByUserName(String username);
}
