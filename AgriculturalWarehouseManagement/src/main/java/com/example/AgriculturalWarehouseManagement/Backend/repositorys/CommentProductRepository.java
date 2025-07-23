package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.CommentProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentProductRepository extends JpaRepository<CommentProduct, Long> {
    @Query(value = """
                    SELECT\s
                        ROUND(AVG(cp.Rating), 2) AS average_rating, \s
                        SUM(CASE WHEN cp.Rating = 5 THEN 1 ELSE 0 END) AS rating_5,
                        SUM(CASE WHEN cp.Rating = 4 THEN 1 ELSE 0 END) AS rating_4,
                        SUM(CASE WHEN cp.Rating = 3 THEN 1 ELSE 0 END) AS rating_3,
                        SUM(CASE WHEN cp.Rating = 2 THEN 1 ELSE 0 END) AS rating_2,
                        SUM(CASE WHEN cp.Rating = 1 THEN 1 ELSE 0 END) AS rating_1
                    FROM commentproduct cp
                    WHERE cp.productid = :productID
                    AND cp.Status = 'Visible'
            """, nativeQuery = true)
    List<Object[]> rawOverallRatingByProductID(Integer productID);

    @Query(value = """
                    SELECT cp.CommentProductID, cp.productid, cp.UserID, u.UserName, u.Image, cp.CreatedAt, cp.Rating, cp.CommentText, cp.dislikes, cp.likes, cp.Status FROM commentproduct cp
                    JOIN user u on cp.UserID = u.UserID
                    WHERE cp.productid = :productID
                    AND cp.Status = 'Visible'
            """, nativeQuery = true)
    List<Object[]> rawViewCommentProduct(Integer productID);
}
