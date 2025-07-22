package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    List<ProductDetail> findProductDetailsByProductID(Product productID);

    @Query(value = """
                    SELECT p.ProductDetailID, p.productid, p.CategoryWeightID, cw.CategoryID, p.Expiry, p.price,  CONCAT(cw.weight, ' ', cw.unit) AS productWeight
                    FROM productdetail p
                    JOIN categoryweight cw on p.CategoryWeightID = cw.CategoryWeightID
                    WHERE p.productid = :productId AND p.status = 'ACTIVE' 
                    order by productid asc
            """, nativeQuery = true)
    List<Object[]> rawGetWeightCompareProductDetails(Integer productId);

    // Get productdetails
    @Query(value = """
                    WITH avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                        SELECT \s
                            ProductID,
                            ROUND(AVG(Rating)) AS avgRating,
                            COUNT(*)           AS ratingCount
                        FROM CommentProduct
                        WHERE CommentProduct.status = 'Visible'
                        GROUP BY ProductID
                    ),\s
                    first_img AS (                                -- Ảnh đầu tiên
                        SELECT g.ProductID, g.ImageURL
                        FROM Gallery g
                        JOIN (
                            SELECT ProductID, MIN(GalleryID) AS min_id
                            FROM Gallery
                            GROUP BY ProductID
                        ) f ON f.ProductID = g.ProductID
                           AND f.min_id = g.GalleryID
                    )
                    
                    SELECT
                        p.CategoryID,
                        p.productid,
                        p.productname,
                        p.description,
                        fi.imageurl,
                        pd.ProductDetailID,
                        CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
                        pb.BatchID,
                        pb.importedquantity,
                        pb.soldquantity,
                        pb.manufacturedate,
                        pd.expiry,
                        pd.price,
                        DATE_ADD(pb.manufacturedate, INTERVAL pd.expiry MONTH) AS expiry_date,
                    
                        -- Tổng số lượng bị điều chỉnh giảm
                        COALESCE(SUM(
                            CASE\s
                                WHEN a.adjustmenttype = 'REMOVE' THEN -a.adjustmentquantity
                                WHEN a.adjustmenttype = 'ADD' THEN a.adjustmentquantity
                                ELSE 0
                            END
                        ), 0) AS total_adjusted_remove_quantity,
                    
                        -- Số lượng còn lại
                        (pb.importedquantity - pb.soldquantity + COALESCE(SUM(
                            CASE\s
                                WHEN a.adjustmenttype = 'REMOVE' THEN -a.adjustmentquantity
                                WHEN a.adjustmenttype = 'ADD' THEN a.adjustmentquantity
                                ELSE 0
                            END
                        ), 0)) AS remaining_quantity,
                    
                        -- Trạng thái hàng tồn kho
                        CASE\s
                            WHEN (pb.importedquantity - pb.soldquantity + COALESCE(SUM(
                                    CASE\s
                                        WHEN a.adjustmenttype = 'REMOVE' THEN -a.adjustmentquantity
                                        WHEN a.adjustmenttype = 'ADD' THEN a.adjustmentquantity
                                        ELSE 0
                                    END
                                ), 0)) > 0 THEN 'Còn hàng'
                            ELSE 'Hết hàng'
                        END AS status,
                    
                        -- Trạng thái hạn sử dụng
                        CASE\s
                            WHEN DATE_ADD(pb.manufacturedate, INTERVAL pd.expiry MONTH) < CURRENT_DATE THEN 'Hết hạn'
                            ELSE 'Còn hạn'
                        END AS expiry_status,
                    
                        -- Điểm trung bình và số lượt đánh giá
                        COALESCE(ar.avgRating, 0) AS avgRating,
                        COALESCE(ar.ratingCount, 0) AS ratingCount
                    
                    FROM product p
                    JOIN first_img fi ON fi.ProductID = p.ProductID
                    JOIN productdetail pd ON p.productid = pd.productid
                    JOIN categoryweight cw ON pd.CategoryWeightID = cw.CategoryWeightID
                    JOIN productbatch pb ON pb.ProductDetailID = pd.ProductDetailID
                    LEFT JOIN adjustment a ON a.batchid = pb.BatchID
                    LEFT JOIN avg_rating ar ON ar.ProductID = p.ProductID   -- Kết nối bảng rating
                    
                    WHERE pd.ProductDetailID = :productDetailId
                      AND p.status = 'ACTIVE'\s
                      AND pd.status = 'ACTIVE'
                    
                    GROUP BY
                        p.CategoryID,
                        p.productid,
                        p.productname,
                        p.description,
                        fi.imageurl,
                        pd.ProductDetailID,
                        pb.BatchID,
                        pb.importedquantity,
                        pb.soldquantity,
                        pb.manufacturedate,
                        pd.expiry,
                        pd.price,
                        cw.weight,
                        cw.unit,
                        ar.avgRating,
                        ar.ratingCount;
            """, nativeQuery = true)
    List<Object[]> rawGetProductDetails(Integer productDetailId);

    // Get product
    @Query(value = """
                    WITH avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                        SELECT \s
                            ProductID,
                            ROUND(AVG(Rating)) AS avgRating,
                            COUNT(*)           AS ratingCount
                        FROM CommentProduct
                        WHERE CommentProduct.status = 'Visible'
                        GROUP BY ProductID
                    ),\s
                    first_img AS (                                -- Ảnh đầu tiên
                        SELECT g.ProductID, g.ImageURL
                        FROM Gallery g
                        JOIN (
                            SELECT ProductID, MIN(GalleryID) AS min_id
                            FROM Gallery
                            GROUP BY ProductID
                        ) f ON f.ProductID = g.ProductID
                           AND f.min_id = g.GalleryID
                    )
                    
                    SELECT
                        p.CategoryID,
                        p.productid,
                        p.productname,
                        p.description,
                        fi.imageurl,
                        pd.ProductDetailID,
                        CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
                        pb.BatchID,
                        pb.importedquantity,
                        pb.soldquantity,
                        pb.manufacturedate,
                        pd.expiry,
                        pd.price,
                        DATE_ADD(pb.manufacturedate, INTERVAL pd.expiry MONTH) AS expiry_date,
                    
                        -- Tổng số lượng bị điều chỉnh giảm
                        COALESCE(SUM(
                            CASE\s
                                WHEN a.adjustmenttype = 'REMOVE' THEN -a.adjustmentquantity
                                WHEN a.adjustmenttype = 'ADD' THEN a.adjustmentquantity
                                ELSE 0
                            END
                        ), 0) AS total_adjusted_remove_quantity,
                    
                        -- Số lượng còn lại
                        (pb.importedquantity - pb.soldquantity + COALESCE(SUM(
                            CASE\s
                                WHEN a.adjustmenttype = 'REMOVE' THEN -a.adjustmentquantity
                                WHEN a.adjustmenttype = 'ADD' THEN a.adjustmentquantity
                                ELSE 0
                            END
                        ), 0)) AS remaining_quantity,
                    
                        -- Trạng thái hàng tồn kho
                        CASE\s
                            WHEN (pb.importedquantity - pb.soldquantity + COALESCE(SUM(
                                    CASE\s
                                        WHEN a.adjustmenttype = 'REMOVE' THEN -a.adjustmentquantity
                                        WHEN a.adjustmenttype = 'ADD' THEN a.adjustmentquantity
                                        ELSE 0
                                    END
                                ), 0)) > 0 THEN 'Còn hàng'
                            ELSE 'Hết hàng'
                        END AS status,
                    
                        -- Trạng thái hạn sử dụng
                        CASE\s
                            WHEN DATE_ADD(pb.manufacturedate, INTERVAL pd.expiry MONTH) < CURRENT_DATE THEN 'Hết hạn'
                            ELSE 'Còn hạn'
                        END AS expiry_status,
                    
                        -- Điểm trung bình và số lượt đánh giá
                        COALESCE(ar.avgRating, 0) AS avgRating,
                        COALESCE(ar.ratingCount, 0) AS ratingCount
                    
                    FROM product p
                    JOIN first_img fi ON fi.ProductID = p.ProductID
                    JOIN productdetail pd ON p.productid = pd.productid
                    JOIN categoryweight cw ON pd.CategoryWeightID = cw.CategoryWeightID
                    JOIN productbatch pb ON pb.ProductDetailID = pd.ProductDetailID
                    LEFT JOIN adjustment a ON a.batchid = pb.BatchID
                    LEFT JOIN avg_rating ar ON ar.ProductID = p.ProductID   -- Kết nối bảng rating
                    
                    WHERE p.productid = :productId
                      AND p.status = 'ACTIVE'\s
                      AND pd.status = 'ACTIVE'
                    
                    GROUP BY
                        p.CategoryID,
                        p.productid,
                        p.productname,
                        p.description,
                        fi.imageurl,
                        pd.ProductDetailID,
                        pb.BatchID,
                        pb.importedquantity,
                        pb.soldquantity,
                        pb.manufacturedate,
                        pd.expiry,
                        pd.price,
                        cw.weight,
                        cw.unit,
                        ar.avgRating,
                        ar.ratingCount;
            """, nativeQuery = true)
    List<Object[]> rawGetProductbyIds(Integer productId);

    // Get trending products
    @Query(value = """
                    WITH avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                                        SELECT\s
                                            ProductID,
                                            ROUND(AVG(Rating)) AS avgRating,
                                            COUNT(*)           AS ratingCount
                                        FROM CommentProduct
                                        WHERE CommentProduct.status = 'Visible'
                                        GROUP BY ProductID
                                    ),
                                    first_img AS (                                -- Ảnh đầu tiên
                                        SELECT g.ProductID, g.ImageURL
                                        FROM Gallery g
                                        JOIN (
                                            SELECT ProductID, MIN(GalleryID) AS min_id
                                            FROM Gallery
                                            GROUP BY ProductID
                                        ) f ON f.ProductID = g.ProductID
                                           AND f.min_id = g.GalleryID
                                    )
                
                                    SELECT
                                        p.CategoryID,
                                        p.productid,
                                        p.productname,
                                        p.description,
                                        fi.imageurl,
                                        pd.ProductDetailID,
                                        CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
                                        pb.BatchID,
                                        pb.importedquantity,
                                        pb.soldquantity,
                                        pb.manufacturedate,
                                        pd.expiry,
                                        pd.price,
                                        DATE_ADD(pb.manufacturedate, INTERVAL pd.expiry MONTH) AS expiry_date,
                
                                        -- Tổng số lượng bị điều chỉnh giảm
                                        COALESCE(SUM(
                                            CASE
                                                WHEN a.adjustmenttype = 'REMOVE' THEN -a.adjustmentquantity
                                                WHEN a.adjustmenttype = 'ADD' THEN a.adjustmentquantity
                                                ELSE 0
                                            END
                                        ), 0) AS total_adjusted_remove_quantity,
                
                                        -- Số lượng còn lại
                                        (pb.importedquantity - pb.soldquantity + COALESCE(SUM(
                                            CASE
                                                WHEN a.adjustmenttype = 'REMOVE' THEN -a.adjustmentquantity
                                                WHEN a.adjustmenttype = 'ADD' THEN a.adjustmentquantity
                                                ELSE 0
                                            END
                                        ), 0)) AS remaining_quantity,
                
                                        -- Trạng thái hàng tồn kho
                                        CASE
                                            WHEN (pb.importedquantity - pb.soldquantity + COALESCE(SUM(
                                                    CASE
                                                        WHEN a.adjustmenttype = 'REMOVE' THEN -a.adjustmentquantity
                                                        WHEN a.adjustmenttype = 'ADD' THEN a.adjustmentquantity
                                                        ELSE 0
                                                    END
                                                ), 0)) > 0 THEN 'Còn hàng'
                                            ELSE 'Hết hàng'
                                        END AS status,
                
                                        -- Trạng thái hạn sử dụng
                                        CASE
                                            WHEN DATE_ADD(pb.manufacturedate, INTERVAL pd.expiry MONTH) < CURRENT_DATE THEN 'Hết hạn'
                                            ELSE 'Còn hạn'
                                        END AS expiry_status,
                
                                        -- Điểm trung bình và số lượt đánh giá
                                        COALESCE(ar.avgRating, 0) AS avgRating,
                                        COALESCE(ar.ratingCount, 0) AS ratingCount
                
                                    FROM product p
                                    JOIN first_img fi ON fi.ProductID = p.ProductID
                                    JOIN productdetail pd ON p.productid = pd.productid
                                    JOIN categoryweight cw ON pd.CategoryWeightID = cw.CategoryWeightID
                                    JOIN productbatch pb ON pb.ProductDetailID = pd.ProductDetailID
                                    LEFT JOIN adjustment a ON a.batchid = pb.BatchID
                                    LEFT JOIN avg_rating ar ON ar.ProductID = p.ProductID   -- Kết nối bảng rating
                
                                    WHERE p.status = 'ACTIVE'
                                      AND pd.status = 'ACTIVE'
                
                                    GROUP BY
                                        p.CategoryID,
                                        p.productid,
                                        p.productname,
                                        p.description,
                                        fi.imageurl,
                                        pd.ProductDetailID,
                                        pb.BatchID,
                                        pb.importedquantity,
                                        pb.soldquantity,
                                        pb.manufacturedate,
                                        pd.expiry,
                                        pd.price,
                                        cw.weight,
                                        cw.unit,
                                        ar.avgRating,
                                        ar.ratingCount
                            ORDER BY soldquantity desc
                            LIMIT 5
            """, nativeQuery = true)
    List<Object[]> rawgetTrendingProduct();
}
