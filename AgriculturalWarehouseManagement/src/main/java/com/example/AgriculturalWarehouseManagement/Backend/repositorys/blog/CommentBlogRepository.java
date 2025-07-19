package com.example.AgriculturalWarehouseManagement.Backend.repositorys.blog;

import com.example.AgriculturalWarehouseManagement.Backend.models.CommentBlog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentBlogRepository extends JpaRepository<CommentBlog, Integer> {
    // Tìm tất cả comments theo Blog ID
    List<CommentBlog> findByBlog_BlogID(Integer blogId);

    // Có thể bổ sung thêm hàm lấy theo trạng thái nếu cần thiết
    List<CommentBlog> findByBlog_BlogIDAndStatus(Integer blogId, String status);
}
