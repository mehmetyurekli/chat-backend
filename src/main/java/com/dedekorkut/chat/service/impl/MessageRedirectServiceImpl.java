package com.dedekorkut.chat.service.impl;

import com.dedekorkut.chat.dto.CreateMessageDto;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.service.ChatService;
import com.dedekorkut.chat.service.MessageRedirectService;
import com.dedekorkut.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageRedirectServiceImpl implements MessageRedirectService {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final ChatService chatService;

    @Override
    public void redirect(CreateMessageDto message) {
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
