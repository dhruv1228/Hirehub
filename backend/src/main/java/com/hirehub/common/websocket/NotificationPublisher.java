package com.hirehub.common.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendToUser(Long userId,
                           String title,
                           String message) {

        messagingTemplate.convertAndSend(
                "/topic/notifications/" + userId,
                NotificationMessage.builder()
                        .title(title)
                        .message(message)
                        .build()
        );
    }
}