package com.hirehub.notification.service;

import com.hirehub.notification.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {

    void createNotification(
            Long userId,
            String title,
            String message,
            com.hirehub.notification.enums.NotificationType type
    );

    List<NotificationResponse> getMyNotifications();

    void markAsRead(Long notificationId);

    long unreadCount();
}