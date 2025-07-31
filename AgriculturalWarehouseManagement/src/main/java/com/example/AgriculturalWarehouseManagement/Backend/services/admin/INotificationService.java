package com.example.AgriculturalWarehouseManagement.Backend.services.admin;

import com.example.AgriculturalWarehouseManagement.Backend.models.Notification;

import java.util.List;

public interface INotificationService {
    List<Notification> getUserNotifications(Integer userId);
    void markAsSeen(Long notificationId);
    Notification createNotification(Notification notification);
    long countUnseenNotifications(Integer userId);

    Notification findById(Long notificationId);

    void markAllAsReadByUserId(Integer userId);

    void achieveNotification(Long notificationId);

    List<Notification> findTop10ByReceiverUserIdOrderByCreatedAtDesc(Integer userId);
}

