package com.example.AgriculturalWarehouseManagement.Backend.services.admin;

import com.example.AgriculturalWarehouseManagement.Backend.models.Notification;
import com.example.AgriculturalWarehouseManagement.Backend.repositorys.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public List<Notification> getUserNotifications(Integer userId) {
        return notificationRepository.findByReceiver_UserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public void markAsSeen(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(n -> {
            n.setStatus("SEEN");
            notificationRepository.save(n);
        });
    }

    @Override
    public void markAllAsReadByUserId(Integer userId) {
        notificationRepository.markAllAsReadByUserId(userId);
    }

    @Transactional
    @Override
    public void achieveNotification(Long notificationId) {
        notificationRepository.achieveNotificationById(notificationId);
    }

    @Override
    public List<Notification> findTop10ByReceiverUserIdOrderByCreatedAtDesc(Integer userId) {
        Pageable pageable = PageRequest.of(0, 10);
        return notificationRepository.findTopByUserId(userId, pageable);
    }

    @Override
    public Notification createNotification(Notification notification) {
        notification.setStatus("UNSEEN");
        notification.setCreatedAt(LocalDateTime.now());
        return notificationRepository.save(notification);
    }

    @Override
    public long countUnseenNotifications(Integer userId) {
        return notificationRepository.countByReceiver_UserIdAndStatus(userId, "UNSEEN");
    }

    @Override
    public Notification findById(Long notificationId) {
        return notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public void sendNotificationToAdmin(Notification notification) {
        messagingTemplate.convertAndSend("/topic/notifications", notification);
    }

}
