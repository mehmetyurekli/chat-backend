package com.dedekorkut.chat.controller;

import com.dedekorkut.chat.dto.CreateMessageDto;
import com.dedekorkut.chat.service.MessageRedirectService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AppController {

    private final MessageRedirectService messageRedirectService;

    @MessageMapping("/sendMessage")
    public void handleMessage(@Payload CreateMessageDto message) {
        messageRedirectService.redirect(message);
    }

}
