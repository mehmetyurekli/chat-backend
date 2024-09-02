package com.dedekorkut.chat.controller;

import com.dedekorkut.chat.dto.CreateMessageDto;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.service.ChatService;
import com.dedekorkut.chat.service.MessageService;
import com.dedekorkut.chat.service.UserService;
import com.dedekorkut.chat.util.ChatType;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AppController {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final ChatService chatService;

    @MessageMapping("/sendMessage")
    public void handleMessage(@Payload CreateMessageDto message) {
        System.out.println("Received message: " + message);
        messageService.create(message);

        Chat chat = chatService.findById(message.getChatId()).getBody();

        if (chat != null) {
            List<String> members = chat.getMembers();

            for (String id : members) {
                messagingTemplate.convertAndSend("/queue/" + id, message);
            }
        }
    }

}
