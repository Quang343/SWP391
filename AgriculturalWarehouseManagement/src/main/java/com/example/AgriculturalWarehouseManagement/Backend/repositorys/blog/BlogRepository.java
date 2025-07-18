package com.example.AgriculturalWarehouseManagement.Backend.repositorys.blog;

import com.example.AgriculturalWarehouseManagement.Backend.models.Blog;
import com.example.AgriculturalWarehouseManagement.Backend.models.BlogStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

    List<Blog> findAllByStatus(BlogStatus status);

    @Query("SELECT b FROM Blog b LEFT JOIN FETCH b.blogDetail WHERE b.blogID = :id")
    Blog findByIdWithDetail(@Param("id") Integer id);

    // Lấy N bài mới nhất (dùng native query)
    // Dùng String status thay vì enum
    @Query(
            value = "SELECT b.*, bd.thumbnail " +
                    "FROM blog b " +
                    "LEFT JOIN blogdetail bd ON b.blogid = bd.blogid " +
                    "WHERE b.status = :status " +
                    "ORDER BY b.createdat DESC " +
                    "LIMIT :count",
            nativeQuery = true)
    List<Object[]> findTopNByStatus(@Param("status") String status, @Param("count") int count);

    @Query("SELECT b FROM Blog b LEFT JOIN FETCH b.blogDetail WHERE b.status = :status")
    List<Blog> findAllActiveWithDetail(@Param("status") BlogStatus status);


    Page<Blog> findAllByStatus(BlogStatus status, Pageable pageable);

    // Phân trang, sắp xếp bài mới nhất trước
    @Query("SELECT b FROM Blog b WHERE b.status = :status ORDER BY b.createdAt DESC")
    Page<Blog> findAllByStatusOrderByCreatedAtDesc(@Param("status") BlogStatus status, Pageable pageable);

    @Query("SELECT b FROM Blog b WHERE b.status = :status AND (" +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) ) " +
            "ORDER BY b.createdAt DESC")
    Page<Blog> searchByAuthorOrContent(@Param("status") BlogStatus status,
                                       @Param("keyword") String keyword,
                                       Pageable pageable);

    // User CRUD
    // Lấy blog của 1 user cụ thể (kèm status nếu muốn)
    @Query("SELECT b FROM Blog b LEFT JOIN FETCH b.blogDetail WHERE b.userID = :userId")
    List<Blog> findByUserIdWithDetail(@Param("userId") Long userId);

    // Nếu muốn phân trang + filter status
    Page<Blog> findByUserIDAndStatusOrderByCreatedAtDesc(Long userId, BlogStatus status, Pageable pageable);

    // Nếu chỉ cần lấy tất cả blog cá nhân (không phân trang)
    List<Blog> findByUserID(Long userId);

    // Hàm này sẽ trả về mọi blog của user (không quan tâm status)
    // , có phân trang, sắp xếp mới nhất lên trước.
    Page<Blog> findByUserIDOrderByCreatedAtDesc(Long userId, Pageable pageable);

    // Thêm annotation @EntityGraph cho method findAll (phân trang)
    @EntityGraph(attributePaths = "blogCategory")
    Page<Blog> findAll(Pageable pageable);

//    // Đếm số lượng blog của một user với trạng thái ACTIVE
//    @Query("SELECT COUNT(b) FROM Blog b WHERE b.userID = :userId AND b.status = :status")
//    Long countBlogsByUserAndStatus(@Param("userId") Long userId, @Param("status") BlogStatus status);
//
//    // Đếm số lượng blog của một user, không phân biệt trạng thái
//    @Query("SELECT COUNT(b) FROM Blog b WHERE b.userID = :userId")
//    Long countBlogsByUser(@Param("userId") Long userId);

//    // Đếm số lượng blog của một user với trạng thái ACTIVE
//    @Query("SELECT COUNT(b) FROM Blog b WHERE b.userId = :userId AND b.status = :status")
//    Long countBlogsByUserAndStatus(@Param("userId") int userId, @Param("status") BlogStatus status);
//
//    // Đếm số lượng blog của một user, không phân biệt trạng thái
//    @Query("SELECT COUNT(b) FROM Blog b WHERE b.userId = :userId")
//    Long countBlogsByUser(@Param("userId") int userId);


}
