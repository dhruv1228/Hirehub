package com.hirehub.common.websocket;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationMessage {

    private String title;

    private String message;
}