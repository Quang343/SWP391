package com.example.AgriculturalWarehouseManagement.Backend.repositorys;

import com.example.AgriculturalWarehouseManagement.Backend.models.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByReceiver_UserIdOrderByCreatedAtDesc(Integer userId);

    @Query("SELECT n FROM Notification n WHERE n.receiver.userId = :userId AND n.status != 'ACHIEVED' ORDER BY n.createdAt DESC")
    List<Notification> findTopByUserId(@Param("userId") Integer userId, Pageable pageable);


    long countByReceiver_UserIdAndStatus(Integer userId, String status);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.status = 'SEEN' WHERE n.receiver.userId = :userId AND n.status = 'UNSEEN'")
    void markAllAsReadByUserId(@Param("userId") Integer userId);

    @Modifying
    @Query("update Notification n set n.status = 'ACHIEVED' where n.id = :id")
    void achieveNotificationById(@Param("id") Long id);

}
