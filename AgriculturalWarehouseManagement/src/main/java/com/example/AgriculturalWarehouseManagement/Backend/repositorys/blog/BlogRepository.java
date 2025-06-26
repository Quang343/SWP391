package com.example.AgriculturalWarehouseManagement.Backend.repositorys.blog;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findAllByStatus(String status);

    @Query("SELECT b FROM Blog b LEFT JOIN FETCH b.blogDetail WHERE b.blogID = :id")
    Blog findByIdWithDetail(@Param("id") Integer id);

    // Lấy N bài mới nhất (dùng native query)
    @Query(
            value = "SELECT b.*, bd.thumbnail " +
                    "FROM blog b " +
                    "LEFT JOIN blogdetail bd ON b.blogid = bd.blogid " +
                    "WHERE b.status = :status " +
                    "ORDER BY b.createdat DESC " +
                    "LIMIT :count",
            nativeQuery = true)
    List<Object[]> findTopNByStatusOrderByCreatedAtDescWithThumbnail(@Param("status") String status, @Param("count") int count);

    @Query("SELECT b FROM Blog b LEFT JOIN FETCH b.blogDetail WHERE b.status = :status")
    List<Blog> findAllActiveWithDetail(@Param("status") String status);

    Page<Blog> findAllByStatus(String status, Pageable pageable);

    // Phân trang, sắp xếp bài mới nhất trước
    @Query("SELECT b FROM Blog b WHERE b.status = :status ORDER BY b.createdAt DESC")
    Page<Blog> findAllByStatusOrderByCreatedAtDesc(@Param("status") String status, Pageable pageable);

    @Query("SELECT b FROM Blog b WHERE b.status = :status AND (" +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) ) " +
            "ORDER BY b.createdAt DESC")
    Page<Blog> searchByAuthorOrContent(@Param("status") String status,
                                       @Param("keyword") String keyword,
                                       Pageable pageable);


}
