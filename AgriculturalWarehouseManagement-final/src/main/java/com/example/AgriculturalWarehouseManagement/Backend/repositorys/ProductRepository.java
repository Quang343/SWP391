package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Product;
import com.example.AgriculturalWarehouseManagement.Backend.models.ProductStatus;
import com.example.AgriculturalWarehouseManagement.Backend.dtos.responses.user.ProductUserHomepageResponse;
import com.example.AgriculturalWarehouseManagement.Backend.models.CommentProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    boolean existsByNameIgnoreCase(String name);

    Page<Product> findByCategory_CategoryId(int categoryCategoryId, Pageable pageable);
    Page<Product> findByStatus(ProductStatus status, Pageable pageable);
    Page<Product> findByCategory_CategoryIdAndStatus(int categoryId, ProductStatus status, Pageable pageable);

    //thanh
//    @Query("""
//    SELECT
//        p.id AS productId,
//        p.name AS productName,
//        SUM(pb.importedQuantity - pb.soldQuantity) AS totalRemaining
//    FROM Product p
//    JOIN p.productDetails pd
//    JOIN pd.productBatches pb
//    GROUP BY p.id, p.name
//    """)
//    List<ProductStockProjection> getRemainingStockByProduct();
//
//    @Query("""
//    SELECT SUM(pb.importedQuantity - pb.soldQuantity)
//    FROM ProductBatch pb
//    """)
//    Integer getTotalRemainingAllProducts();

    // Lấy ảnh đầu tiên của mỗi sản phẩm (ProductID) trong bảng Gallery, dựa trên GalleryID nhỏ nhất
    // Lấy AVG(Rating) theo ProductID giống nhau tính avg, và đếm số lượng rating khi productid giống nhau
    // Nếu productId giống nhau trong productDetailId thì lấy price nhỏ nhất trong productDetailId
    // Common table Expressions
    // Get list product of Homepage
    @Query(value = """
                                    WITH first_img AS (                      -- 1 ảnh đầu tiên
                                        SELECT g.ProductID, g.ImageURL
                                        FROM Gallery g
                                        JOIN (
                                            SELECT ProductID, MIN(GalleryID) AS min_id
                                            FROM Gallery
                                            GROUP BY ProductID
                                        ) f ON f.ProductID = g.ProductID
                                           AND f.min_id    = g.GalleryID
                                    ),
                                    avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                                        SELECT  ProductID,
                                                ROUND(AVG(Rating)) AS avgRating,
                                                COUNT(*)           AS ratingCount
                                        FROM CommentProduct
                                        GROUP BY ProductID
                                    ),
                                    cheapest_detail AS (                     -- ★ 1 dòng rẻ nhất cho mỗi sản phẩm
                                        SELECT  pd.*,
                                                ROW_NUMBER() OVER (PARTITION BY pd.ProductID
                                                                   ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
                                        FROM ProductDetail pd
                                    )
                                    SELECT  p.ProductID               AS productId,
                                            p.CategoryID              AS categoryId,
                                            fi.ImageURL               AS imageUrlGallery,
                                            p.ProductName             AS productName,
                                            COALESCE(ar.avgRating,0)  AS ratingProductDetail,
                                            COALESCE(ar.ratingCount,0)AS ratingCount,
                                            cd.DetailName             AS productDetailName,
                                            cd.Price                  AS productPrice,
            
                                            -- Lấy weight + unit thông qua CategoryWeightID
                                            CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
            
                                            p.Description             AS productDescription
                                    FROM       Product         p
                                    JOIN       cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
                                    JOIN       categoryWeight  cw ON cw.CategoryWeightID = cd.CategoryWeightID   -- ★ JOIN mới
                                    LEFT JOIN  first_img       fi ON fi.ProductID = p.ProductID
                                    LEFT JOIN  avg_rating      ar ON ar.ProductID = p.ProductID
                                    WHERE      p.Status = 'ACTIVE';
            """, nativeQuery = true)
    List<Object[]> rawHomepageData();

    // Get product menu of Homepage
    @Query(value = """
                                    WITH first_img AS (                      -- 1 ảnh đầu tiên
                                        SELECT g.ProductID, g.ImageURL
                                        FROM Gallery g
                                        JOIN (
                                            SELECT ProductID, MIN(GalleryID) AS min_id
                                            FROM Gallery
                                            GROUP BY ProductID
                                        ) f ON f.ProductID = g.ProductID
                                           AND f.min_id    = g.GalleryID
                                    ),
                                    avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                                        SELECT  ProductID,
                                                ROUND(AVG(Rating)) AS avgRating,
                                                COUNT(*)           AS ratingCount
                                        FROM CommentProduct
                                        GROUP BY ProductID
                                    ),
                                    cheapest_detail AS (                     -- ★ 1 dòng rẻ nhất cho mỗi sản phẩm
                                        SELECT  pd.*,
                                                ROW_NUMBER() OVER (PARTITION BY pd.ProductID
                                                                   ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
                                        FROM ProductDetail pd
                                    )
                                    SELECT  p.ProductID               AS productId,
                                            p.CategoryID              AS categoryId,
                                            fi.ImageURL               AS imageUrlGallery,
                                            p.ProductName             AS productName,
                                            COALESCE(ar.avgRating,0)  AS ratingProductDetail,
                                            COALESCE(ar.ratingCount,0)AS ratingCount,
                                            cd.DetailName             AS productDetailName,
                                            cd.Price                  AS productPrice,
            
                                            -- Lấy weight + unit thông qua CategoryWeightID
                                            CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
            
                                            p.Description             AS productDescription
                                    FROM       Product         p
                                    JOIN       cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
                                    JOIN       categoryWeight  cw ON cw.CategoryWeightID = cd.CategoryWeightID   -- ★ JOIN mới
                                    LEFT JOIN  first_img       fi ON fi.ProductID = p.ProductID
                                    LEFT JOIN  avg_rating      ar ON ar.ProductID = p.ProductID
                                    WHERE      p.Status = 'ACTIVE'
                                    ORDER BY p.CategoryID asc;
            """, nativeQuery = true)
    List<Object[]> rawproductMenuHomepageData();

    // Top 9 new of product
    @Query(value = """
                WITH first_img AS (                      -- 1 ảnh đầu tiên
                    SELECT g.ProductID, g.ImageURL
                    FROM Gallery g
                    JOIN (
                        SELECT ProductID, MIN(GalleryID) AS min_id
                        FROM Gallery
                        GROUP BY ProductID
                    ) f ON f.ProductID = g.ProductID
                       AND f.min_id    = g.GalleryID
                ),
                avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                    SELECT  ProductID,
                            ROUND(AVG(Rating)) AS avgRating,
                            COUNT(*)           AS ratingCount
                    FROM CommentProduct
                    GROUP BY ProductID
                ),
                cheapest_detail AS (                     -- ★ 1 dòng rẻ nhất cho mỗi sản phẩm
                    SELECT  pd.*,
                            ROW_NUMBER() OVER (PARTITION BY pd.ProductID
                                               ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
                    FROM ProductDetail pd
                )
                SELECT  p.ProductID               AS productId,
                        p.CategoryID              AS categoryId,
                        fi.ImageURL               AS imageUrlGallery,
                        p.ProductName             AS productName,
                        COALESCE(ar.avgRating,0)  AS ratingProductDetail,
                        COALESCE(ar.ratingCount,0)AS ratingCount,
                        cd.DetailName             AS productDetailName,
                        cd.Price                  AS productPrice,
            
                        -- Lấy weight + unit thông qua CategoryWeightID
                        CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
            
                        p.Description             AS productDescription
                FROM       Product         p
                JOIN       cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
                JOIN       categoryWeight  cw ON cw.CategoryWeightID = cd.CategoryWeightID   -- ★ JOIN mới
                LEFT JOIN  first_img       fi ON fi.ProductID = p.ProductID
                LEFT JOIN  avg_rating      ar ON ar.ProductID = p.ProductID
                WHERE      p.Status = 'ACTIVE'
                ORDER BY   p.ProductID DESC
                 LIMIT 9
            """, nativeQuery = true)
    List<Object[]> top9NewOfProducts();

    // Top 9 of highest price product
    @Query(value = """
                                   WITH first_img AS (                      -- 1 ảnh đầu tiên
                                       SELECT g.ProductID, g.ImageURL
                                       FROM Gallery g
                                       JOIN (
                                           SELECT ProductID, MIN(GalleryID) AS min_id
                                           FROM Gallery
                                           GROUP BY ProductID
                                       ) f ON f.ProductID = g.ProductID
                                          AND f.min_id    = g.GalleryID
                                   ),
                                   avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                                       SELECT  ProductID,
                                               ROUND(AVG(Rating)) AS avgRating,
                                               COUNT(*)           AS ratingCount
                                       FROM CommentProduct
                                       GROUP BY ProductID
                                   ),
                                   cheapest_detail AS (                     -- ★ 1 dòng rẻ nhất cho mỗi sản phẩm
                                       SELECT  pd.*,
                                               ROW_NUMBER() OVER (PARTITION BY pd.ProductID
                                                                  ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
                                       FROM ProductDetail pd
                                   )
                                   SELECT  p.ProductID               AS productId,
                                           p.CategoryID              AS categoryId,
                                           fi.ImageURL               AS imageUrlGallery,
                                           p.ProductName             AS productName,
                                           COALESCE(ar.avgRating,0)  AS ratingProductDetail,
                                           COALESCE(ar.ratingCount,0)AS ratingCount,
                                           cd.DetailName             AS productDetailName,
                                           cd.Price                  AS productPrice,
            
                                           -- Lấy weight + unit thông qua CategoryWeightID
                                           CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
            
                                           p.Description             AS productDescription
                                   FROM       Product         p
                                   JOIN       cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
                                   JOIN       categoryWeight  cw ON cw.CategoryWeightID = cd.CategoryWeightID   -- ★ JOIN mới
                                   LEFT JOIN  first_img       fi ON fi.ProductID = p.ProductID
                                   LEFT JOIN  avg_rating      ar ON ar.ProductID = p.ProductID
                                   WHERE      p.Status = 'ACTIVE'
                                   ORDER BY  cd.Price DESC
                                   LIMIT 9
            """, nativeQuery = true)
    List<Object[]> top9PriceOfProducts();

    // Top 9 of highest rating Product
    @Query(value = """
                                   WITH first_img AS (                      -- 1 ảnh đầu tiên
                                       SELECT g.ProductID, g.ImageURL
                                       FROM Gallery g
                                       JOIN (
                                           SELECT ProductID, MIN(GalleryID) AS min_id
                                           FROM Gallery
                                           GROUP BY ProductID
                                       ) f ON f.ProductID = g.ProductID
                                          AND f.min_id    = g.GalleryID
                                   ),
                                   avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                                       SELECT  ProductID,
                                               ROUND(AVG(Rating)) AS avgRating,
                                               COUNT(*)           AS ratingCount
                                       FROM CommentProduct
                                       GROUP BY ProductID
                                   ),
                                   cheapest_detail AS (                     -- ★ 1 dòng rẻ nhất cho mỗi sản phẩm
                                       SELECT  pd.*,
                                               ROW_NUMBER() OVER (PARTITION BY pd.ProductID
                                                                  ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
                                       FROM ProductDetail pd
                                   )
                                   SELECT  p.ProductID               AS productId,
                                           p.CategoryID              AS categoryId,
                                           fi.ImageURL               AS imageUrlGallery,
                                           p.ProductName             AS productName,
                                           COALESCE(ar.avgRating,0)  AS ratingProductDetail,
                                           COALESCE(ar.ratingCount,0)AS ratingCount,
                                           cd.DetailName             AS productDetailName,
                                           cd.Price                  AS productPrice,
            
                                           -- Lấy weight + unit thông qua CategoryWeightID
                                           CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
            
                                           p.Description             AS productDescription
                                   FROM       Product         p
                                   JOIN       cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
                                   JOIN       categoryWeight  cw ON cw.CategoryWeightID = cd.CategoryWeightID   -- ★ JOIN mới
                                   LEFT JOIN  first_img       fi ON fi.ProductID = p.ProductID
                                   LEFT JOIN  avg_rating      ar ON ar.ProductID = p.ProductID
                                   WHERE      p.Status = 'ACTIVE'
                                   ORDER BY ratingProductDetail DESC
                                   LIMIT 9
            """, nativeQuery = true)
    List<Object[]> top9RatingOfProducts();

    // Shop detail product
    @Query(value = """
                                     WITH first_img AS (                      -- 1 ảnh đầu tiên
                                        SELECT g.ProductID, g.ImageURL
                                        FROM Gallery g
                                        JOIN (
                                            SELECT ProductID, MIN(GalleryID) AS min_id
                                            FROM Gallery
                                            GROUP BY ProductID
                                        ) f ON f.ProductID = g.ProductID
                                           AND f.min_id    = g.GalleryID
                                    ),
                                    avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                                        SELECT  ProductID,
                                                ROUND(AVG(Rating)) AS avgRating,
                                                COUNT(*)           AS ratingCount
                                        FROM CommentProduct
                                        GROUP BY ProductID
                                    ),
                                    cheapest_detail AS (                     -- ★ 1 dòng rẻ nhất cho mỗi sản phẩm
                                        SELECT  pd.*,
                                                ROW_NUMBER() OVER (PARTITION BY pd.ProductID
                                                                   ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
                                        FROM ProductDetail pd
                                    )
                                    SELECT  p.ProductID               AS productId,
                                            p.CategoryID              AS categoryId,
                                            fi.ImageURL               AS imageUrlGallery,
                                            p.ProductName             AS productName,
                                            COALESCE(ar.avgRating,0)  AS ratingProductDetail,
                                            COALESCE(ar.ratingCount,0)AS ratingCount,
                                            cd.DetailName             AS productDetailName,
                                            cd.Price                  AS productPrice,
            
                                            -- Lấy weight + unit thông qua CategoryWeightID
                                            CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
            
                                            p.Description             AS productDescription
                                    FROM       Product         p
                                    JOIN       cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
                                    JOIN       categoryWeight  cw ON cw.CategoryWeightID = cd.CategoryWeightID   -- ★ JOIN mới
                                    LEFT JOIN  first_img       fi ON fi.ProductID = p.ProductID
                                    LEFT JOIN  avg_rating      ar ON ar.ProductID = p.ProductID
                                    WHERE      p.Status = 'ACTIVE'
            """, nativeQuery = true)
    List<Object[]> rawShopDetailsOfProducts();

    // Shop detail product of pages
    @Query(value = """
                                     WITH first_img AS (                      -- 1 ảnh đầu tiên
                                        SELECT g.ProductID, g.ImageURL
                                        FROM Gallery g
                                        JOIN (
                                            SELECT ProductID, MIN(GalleryID) AS min_id
                                            FROM Gallery
                                            GROUP BY ProductID
                                        ) f ON f.ProductID = g.ProductID
                                           AND f.min_id    = g.GalleryID
                                    ),
                                    avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                                        SELECT  ProductID,
                                                ROUND(AVG(Rating)) AS avgRating,
                                                COUNT(*)           AS ratingCount
                                        FROM CommentProduct
                                        GROUP BY ProductID
                                    ),
                                    cheapest_detail AS (                     -- ★ 1 dòng rẻ nhất cho mỗi sản phẩm
                                        SELECT  pd.*,
                                                ROW_NUMBER() OVER (PARTITION BY pd.ProductID
                                                                   ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
                                        FROM ProductDetail pd
                                    )
                                    SELECT  p.ProductID               AS productId,
                                            p.CategoryID              AS categoryId,
                                            fi.ImageURL               AS imageUrlGallery,
                                            p.ProductName             AS productName,
                                            COALESCE(ar.avgRating,0)  AS ratingProductDetail,
                                            COALESCE(ar.ratingCount,0)AS ratingCount,
                                            cd.DetailName             AS productDetailName,
                                            cd.Price                  AS productPrice,
            
                                            -- Lấy weight + unit thông qua CategoryWeightID
                                            CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
            
                                            p.Description             AS productDescription
                                    FROM       Product         p
                                    JOIN       cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
                                    JOIN       categoryWeight  cw ON cw.CategoryWeightID = cd.CategoryWeightID   -- ★ JOIN mới
                                    LEFT JOIN  first_img       fi ON fi.ProductID = p.ProductID
                                    LEFT JOIN  avg_rating      ar ON ar.ProductID = p.ProductID
                                    WHERE      p.Status = 'ACTIVE'
                                    LIMIT :rowStart, 9; -- LIMIT <vị_trí_bắt_đầu>, <số_dòng>
            """, nativeQuery = true)
    List<Object[]> rawShopDetailsOfProductPages(Integer rowStart);


    // Shop detail product by CategoryId
    @Query(value = """
                                     WITH first_img AS (                      -- 1 ảnh đầu tiên
                                        SELECT g.ProductID, g.ImageURL
                                        FROM Gallery g
                                        JOIN (
                                            SELECT ProductID, MIN(GalleryID) AS min_id
                                            FROM Gallery
                                            GROUP BY ProductID
                                        ) f ON f.ProductID = g.ProductID
                                           AND f.min_id    = g.GalleryID
                                    ),
                                    avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                                        SELECT  ProductID,
                                                ROUND(AVG(Rating)) AS avgRating,
                                                COUNT(*)           AS ratingCount
                                        FROM CommentProduct
                                        GROUP BY ProductID
                                    ),
                                    cheapest_detail AS (                     -- ★ 1 dòng rẻ nhất cho mỗi sản phẩm
                                        SELECT  pd.*,
                                                ROW_NUMBER() OVER (PARTITION BY pd.ProductID
                                                                   ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
                                        FROM ProductDetail pd
                                    )
                                    SELECT  p.ProductID               AS productId,
                                            p.CategoryID              AS categoryId,
                                            fi.ImageURL               AS imageUrlGallery,
                                            p.ProductName             AS productName,
                                            COALESCE(ar.avgRating,0)  AS ratingProductDetail,
                                            COALESCE(ar.ratingCount,0)AS ratingCount,
                                            cd.DetailName             AS productDetailName,
                                            cd.Price                  AS productPrice,
            
                                            -- Lấy weight + unit thông qua CategoryWeightID
                                            CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
            
                                            p.Description             AS productDescription
                                    FROM       Product         p
                                    JOIN       cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
                                    JOIN       categoryWeight  cw ON cw.CategoryWeightID = cd.CategoryWeightID   -- ★ JOIN mới
                                    LEFT JOIN  first_img       fi ON fi.ProductID = p.ProductID
                                    LEFT JOIN  avg_rating      ar ON ar.ProductID = p.ProductID
                                    WHERE      p.Status = 'ACTIVE'
                                    AND      p.CategoryID = :categoryId
            
            """, nativeQuery = true)
    List<Object[]> rawShopDetailsOfProductsByCategoryId(Integer categoryId);

    // Shop detail product by CategoryId of page
    @Query(value = """
                                     WITH first_img AS (                      -- 1 ảnh đầu tiên
                                        SELECT g.ProductID, g.ImageURL
                                        FROM Gallery g
                                        JOIN (
                                            SELECT ProductID, MIN(GalleryID) AS min_id
                                            FROM Gallery
                                            GROUP BY ProductID
                                        ) f ON f.ProductID = g.ProductID
                                           AND f.min_id    = g.GalleryID
                                    ),
                                    avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                                        SELECT  ProductID,
                                                ROUND(AVG(Rating)) AS avgRating,
                                                COUNT(*)           AS ratingCount
                                        FROM CommentProduct
                                        GROUP BY ProductID
                                    ),
                                    cheapest_detail AS (                     -- ★ 1 dòng rẻ nhất cho mỗi sản phẩm
                                        SELECT  pd.*,
                                                ROW_NUMBER() OVER (PARTITION BY pd.ProductID
                                                                   ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
                                        FROM ProductDetail pd
                                    )
                                    SELECT  p.ProductID               AS productId,
                                            p.CategoryID              AS categoryId,
                                            fi.ImageURL               AS imageUrlGallery,
                                            p.ProductName             AS productName,
                                            COALESCE(ar.avgRating,0)  AS ratingProductDetail,
                                            COALESCE(ar.ratingCount,0)AS ratingCount,
                                            cd.DetailName             AS productDetailName,
                                            cd.Price                  AS productPrice,
            
                                            -- Lấy weight + unit thông qua CategoryWeightID
                                            CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
            
                                            p.Description             AS productDescription
                                    FROM       Product         p
                                    JOIN       cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
                                    JOIN       categoryWeight  cw ON cw.CategoryWeightID = cd.CategoryWeightID   -- ★ JOIN mới
                                    LEFT JOIN  first_img       fi ON fi.ProductID = p.ProductID
                                    LEFT JOIN  avg_rating      ar ON ar.ProductID = p.ProductID
                                    WHERE      p.Status = 'ACTIVE'
                                    AND      p.CategoryID = :categoryId
                                    LIMIT :rowStart, 9
            
            """, nativeQuery = true)
    List<Object[]> rawShopDetailsOfProductsByCategoryIdPage(Integer categoryId, Integer rowStart);

    // Search product API
    @Query(value = """
                                     WITH first_img AS (                      -- 1 ảnh đầu tiên
                                        SELECT g.ProductID, g.ImageURL
                                        FROM Gallery g
                                        JOIN (
                                            SELECT ProductID, MIN(GalleryID) AS min_id
                                            FROM Gallery
                                            GROUP BY ProductID
                                        ) f ON f.ProductID = g.ProductID
                                           AND f.min_id    = g.GalleryID
                                    ),
                                    avg_rating AS (                          -- Điểm TB + số lượt đánh giá
                                        SELECT  ProductID,
                                                ROUND(AVG(Rating)) AS avgRating,
                                                COUNT(*)           AS ratingCount
                                        FROM CommentProduct
                                        GROUP BY ProductID
                                    ),
                                    cheapest_detail AS (                     -- ★ 1 dòng rẻ nhất cho mỗi sản phẩm
                                        SELECT  pd.*,
                                                ROW_NUMBER() OVER (PARTITION BY pd.ProductID
                                                                   ORDER BY pd.Price ASC, pd.ProductDetailID ASC) AS rn
                                        FROM ProductDetail pd
                                    )
                                    SELECT  p.ProductID               AS productId,
                                            p.CategoryID              AS categoryId,
                                            fi.ImageURL               AS imageUrlGallery,
                                            p.ProductName             AS productName,
                                            COALESCE(ar.avgRating,0)  AS ratingProductDetail,
                                            COALESCE(ar.ratingCount,0)AS ratingCount,
                                            cd.DetailName             AS productDetailName,
                                            cd.Price                  AS productPrice,
            
                                            -- Lấy weight + unit thông qua CategoryWeightID
                                            CONCAT(cw.weight, ' ', cw.unit) AS productWeight,
            
                                            p.Description             AS productDescription
                                    FROM       Product         p
                                    JOIN       cheapest_detail cd ON cd.ProductID = p.ProductID AND cd.rn = 1
                                    JOIN       categoryWeight  cw ON cw.CategoryWeightID = cd.CategoryWeightID   -- ★ JOIN mới
                                    LEFT JOIN  first_img       fi ON fi.ProductID = p.ProductID
                                    LEFT JOIN  avg_rating      ar ON ar.ProductID = p.ProductID
                                    WHERE      p.Status = 'ACTIVE'
                                    AND p.ProductName LIKE CONCAT('%', :keyword, '%')
            
            """, nativeQuery = true)
    List<Object[]> rawProductsSearchAPIs(@Param("keyword") String keyword);

    @Query(value = """
                    SELECT * FROM product p
                    WHERE p.ProductID = :productID
            """, nativeQuery = true)
    Optional<Product> productByProductID(Integer productID);

    @Query(value = """
                    SELECT * FROM gallery g
                    WHERE g.ProductID = :productID
            """, nativeQuery = true)
    List<Object[]> rawGalleryUserByProductID(Integer productID);

}
