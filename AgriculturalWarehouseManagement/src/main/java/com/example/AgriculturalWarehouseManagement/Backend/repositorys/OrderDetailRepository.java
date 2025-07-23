package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long orderId);

    @Query(value = """
            WITH FirstGallery AS (
            	SELECT *
            	FROM (
            		SELECT
            			g.*,
            			ROW_NUMBER() OVER (PARTITION BY g.ProductID ORDER BY g.GalleryID ASC) AS rn
            		FROM gallery g
            	) t
            	WHERE rn = 1
            )
            SELECT o.orderid, o.address as addressShip, o.discount_amount as discountAmount, o.email as emailShip, o.final_amount as finalAmount, o.fullname as fullNameShip, o.note as noteShip, o.ordercode, o.orderdate, o.phone as phoneShip, o.status as orderStatus, o.totalamount, COALESCE(o.voucher_code,'') as voucherCode, od.productdetailid, p.ProductName ,od.price as productTotalPrice, od.quantity, COALESCE(s.UserName,'') as sellerName ,  CONCAT(cw.weight, ' ', cw.unit) AS productWeight, g.imageurl, pd.price,p.ProductID as productID, u.userName as userUseName, u.email as userEmail, COALESCE(u.address,'') as userAddress, COALESCE(u.Phone, '') AS userPhone \s
            FROM `order` o
            JOIN orderdetail od ON o.orderid = od.orderid
            LEFT JOIN soldbyseller sbs ON od.productdetailid = sbs.productdetailid
            LEFT JOIN user s ON s.userid = sbs.userid
            JOIN productdetail pd ON od.productdetailid = pd.productdetailid
            JOIN categoryweight cw ON pd.CategoryWeightID = cw.CategoryWeightID
            JOIN product p ON pd.productid = p.ProductID
            JOIN FirstGallery g on p.ProductID = g.ProductID
            JOIN user u ON o.userid = u.userid
            WHERE o.ordercode = :orderCode\s
            AND o.status IN ("PENDING", "CONFIRMED", "DELIVERED","CANCELLED","STOCKOUT","COMPLETED");
            """,nativeQuery = true)
    List<Map<String, Object>> rawGetOrderDetails(String orderCode);
}
