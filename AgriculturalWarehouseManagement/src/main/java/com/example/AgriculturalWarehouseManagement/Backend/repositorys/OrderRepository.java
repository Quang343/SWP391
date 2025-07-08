package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

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
