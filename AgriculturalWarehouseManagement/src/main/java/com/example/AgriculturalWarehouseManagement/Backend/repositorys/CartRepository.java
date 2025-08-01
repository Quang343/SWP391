package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {

    @Query(value = """
                    WITH FirstGallery AS (
                        SELECT *
                        FROM (
                            SELECT\s
                                g.*,\s
                                ROW_NUMBER() OVER (PARTITION BY g.ProductID ORDER BY g.GalleryID ASC) AS rn
                            FROM gallery g
                        ) t
                        WHERE rn = 1
                    )
                    SELECT\s
                        c.CartID,
                        c.UserID,
                        p.ProductID,
                        pd.ProductDetailID,
                        fg.imageurl,
                        p.ProductName,
                        COALESCE(u.UserName, '') AS sellerName,
                        CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
                    	 pd.price,
                         c.Quantity,
                    	 c.total,
                         c.CreatedAt
                    FROM cart c
                    JOIN productdetail pd ON c.ProductDetailID = pd.ProductDetailID\s
                    JOIN categoryweight cw ON pd.CategoryWeightID = cw.CategoryWeightID
                    JOIN product p ON pd.ProductID = p.ProductID
                    JOIN FirstGallery fg ON p.ProductID = fg.ProductID
                    LEFT JOIN soldbyseller sbs ON sbs.productdetailid = pd.ProductDetailID \s
                    LEFT JOIN user u ON sbs.userid = u.UserID
                    where c.UserID = :userID
            """, nativeQuery = true)
    List<Object[]> rawGetCartByUserID(int userID);

    @Query(value = """
                    SELECT * FROM cart c
                    WHERE c.UserID = :userID
            """,nativeQuery = true)
    List<Object[]> rawGetTableCartByUserID(int userID);

    @Override
    void deleteById(Long id);


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
                   SELECT
                       c.UserID,
                       p.ProductID,
                       pd.ProductDetailID,
                       fg.imageurl,
                       p.ProductName,
                       COALESCE(u.UserName, '') AS sellerName,
                       CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
                       pd.price,
                       SUM(c.Quantity) AS TotalQuantityProductDetail,  -- Cộng lại Quantity
                       SUM(c.total) AS TotalPriceProductDetail        -- Cộng lại Total
                   FROM cart c
                   JOIN productdetail pd ON c.ProductDetailID = pd.ProductDetailID
                   JOIN categoryweight cw ON pd.CategoryWeightID = cw.CategoryWeightID
                   JOIN product p ON pd.ProductID = p.ProductID
                   JOIN FirstGallery fg ON p.ProductID = fg.ProductID
                   LEFT JOIN soldbyseller sbs ON sbs.productdetailid = pd.ProductDetailID\s
                   LEFT JOIN user u ON sbs.userid = u.UserID
                   WHERE c.UserID = :userID
                   GROUP BY
                       c.UserID,
                       p.ProductID,
                       pd.ProductDetailID,
                       fg.imageurl,
                       p.ProductName,
                       u.UserName,
                       cw.weight,
                       cw.unit,
                       pd.price;
            """, nativeQuery = true)
    List<Object[]> rawGetCheckOutProductDetailByUserID(int userID);

    @Query(value = """
                   SELECT *,
                          TIMESTAMPDIFF(MINUTE, NOW(), DATE_ADD(CreatedAt, INTERVAL 1 HOUR)) AS minutes_left
                   FROM managerwarehouse.cart
                   WHERE ProductDetailID = :productDetailId
                     AND DATE_ADD(CreatedAt, INTERVAL 1 HOUR) >= NOW();
            """, nativeQuery = true)
    List<Object[]> rawGetCartQuantity(int productDetailId);
}
