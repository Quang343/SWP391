package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findAllByStatus(String status);

    // Lấy N bài mới nhất (dùng native query)
    @Query(value = "SELECT * FROM blog WHERE Status = :status " +
            "ORDER BY CreatedAt DESC LIMIT :count", nativeQuery = true)
    List<Blog> findTopNByStatusOrderByCreatedAtDesc(@Param("status") String status, @Param("count") int count);


    // BlogRepository.java
    @Query("SELECT b FROM Blog b LEFT JOIN FETCH b.blogDetail WHERE b.status = :status")
    List<Blog> findAllActiveWithDetail(@Param("status") String status);

    Page<Blog> findAllByStatus(String status, Pageable pageable);

    // Phân trang, sắp xếp bài mới nhất trước
    @Query("SELECT b FROM Blog b WHERE b.status = :status ORDER BY b.createdAt DESC")
    Page<Blog> findAllByStatusOrderByCreatedAtDesc(@Param("status") String status, Pageable pageable);

}
