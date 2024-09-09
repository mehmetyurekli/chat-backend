package com.dedekorkut.chat.controller;

import com.dedekorkut.chat.dto.Notification;
import com.dedekorkut.chat.service.MessageRedirectService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AppController {

    private final MessageRedirectService messageRedirectService;

    @MessageMapping("/sendNotification")
    public void handleMessage(@Payload Notification notification) {
        messageRedirectService.redirect(notification);
    }

}
