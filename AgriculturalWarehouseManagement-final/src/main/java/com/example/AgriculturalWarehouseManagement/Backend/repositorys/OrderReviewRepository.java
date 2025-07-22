package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.OrderReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderReviewRepository extends JpaRepository<OrderReview, Long> {

    @Query(value = """
                    SELECT ore.orderreviewid, COALESCE(ore.image, '') AS image , COALESCE(ore.message,'') AS message, ore.rating AS rating,ore.ordercode,  ore.updatedatetime AS updateDateTime , o.orderdate ,ore.updatetimeform, ore.updatetimeimage, o.userid,u.UserName, u.email
                    FROM orderreview ore
                    JOIN `order` o ON ore.ordercode = o.ordercode
                    JOIN user u ON o.userid = u.userid
                    WHERE ore.ordercode = :ordercode;
            """, nativeQuery = true)
    List<Object[]> rawGetOrderReviews(String ordercode);
}
