package com.example.AgriculturalWarehouseManagement.Backend.repositorys;


import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import com.example.AgriculturalWarehouseManagement.Backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findByStatusIsNot(String status);

    boolean existsByOrderCode(String orderCode);

    List<Order> findByStatus(String status);

    // Tổng tất cả order (bao gồm cả đã huỷ)
    @Query("SELECT COUNT(o) FROM Order o")
    long countAllOrders();

    // Tổng order hợp lệ (không bao gồm huỷ)
    @Query("SELECT COUNT(o) FROM Order o WHERE o.status NOT IN ('CANCELLED')")
    long countValidOrders();

    // Hoặc: đếm theo từng trạng thái nếu cần
    @Query("SELECT o.status, COUNT(o) FROM Order o GROUP BY o.status")
    List<Object[]> countOrdersGroupedByStatus();

    @Query(value = """
                    SELECT\s
                        o.orderid , o.status, o.orderdate, o.ordercode, u.Email, o.final_amount ,SUM(od.quantity) AS total_quantity
                    FROM `order` o
                    JOIN orderdetail od ON o.orderid = od.orderid
                    JOIN user u ON o.userid = u.UserID
                    WHERE o.userid = :userId
                    GROUP BY  o.orderid, o.status, o.orderdate, o.ordercode, u.Email, o.final_amount;
            """, nativeQuery = true)
    List<Object[]> rawGetOrders(long userId);
}
