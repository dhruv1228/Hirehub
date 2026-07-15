package com.hirehub.notification.dto;

import com.hirehub.notification.enums.NotificationType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class NotificationResponse {

    private Long id;

    private NotificationType type;

    private String title;

    private String message;

    private Boolean isRead;

    private LocalDateTime createdAt;
}