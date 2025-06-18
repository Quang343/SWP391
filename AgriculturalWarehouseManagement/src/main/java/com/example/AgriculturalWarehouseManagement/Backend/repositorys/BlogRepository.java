package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findAllByStatus(String status);

    // Lấy N bài mới nhất (dùng native query)
    @Query(value = "SELECT * FROM blog WHERE Status = :status ORDER BY CreatedAt DESC LIMIT :count", nativeQuery = true)
    List<Blog> findTopNByStatusOrderByCreatedAtDesc(@Param("status") String status, @Param("count") int count);

}
