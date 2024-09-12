package com.dedekorkut.chat.service.impl;

import com.dedekorkut.chat.dto.CreateMessageDto;
import com.dedekorkut.chat.dto.Notification;
import com.dedekorkut.chat.dto.NotifyBulkReadDto;
import com.dedekorkut.chat.dto.NotifyReadDto;
import com.dedekorkut.chat.dto.response.BulkReadDto;
import com.dedekorkut.chat.dto.response.ReadDto;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.entity.Message;
import com.dedekorkut.chat.service.ChatService;
import com.dedekorkut.chat.service.MessageRedirectService;
import com.dedekorkut.chat.service.MessageService;
import com.dedekorkut.chat.util.NotificationType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageRedirectServiceImpl implements MessageRedirectService {

    private final SimpMessagingTemplate messagingTemplate;
    private final MessageService messageService;
    private final ChatService chatService;
    private final MongoTemplate mongoTemplate;

    @Override
    public void redirect(Notification notification) {

        switch (notification.getType()) {

            case MSG_SENT -> {

                ObjectMapper mapper = new ObjectMapper();
                CreateMessageDto dto = mapper.convertValue(notification.getBody(), CreateMessageDto.class);

                Message message = messageService.create(dto).getBody();

                assert message != null;
                Chat chat = chatService.findById(message.getChatId()).getBody();

                if (chat != null) {
                    notification.setType(NotificationType.MSG_RECEIVED);
                    notification.setBody(message);
                    List<String> members = chat.getMembers();
                    for (String id : members) {
                        messagingTemplate.convertAndSend("/queue/" + id, notification);
                    }
                }
            }

            case MSG_READ -> {
                ObjectMapper mapper = new ObjectMapper();

                NotifyReadDto dto = mapper.convertValue(notification.getBody(), NotifyReadDto.class);

                ReadDto readDto = messageService.updateReadAtSingle(dto).getBody();
                assert readDto != null;

                Chat chat = chatService.findById(readDto.getChatId()).getBody();

                if (chat != null) {
                    notification.setType(NotificationType.MSG_READ);
                    notification.setBody(readDto);
                    List<String> members = chat.getMembers();
                    for (String id : members) {
                        messagingTemplate.convertAndSend("/queue/" + id, notification);
                    }
                }
            }

            case MSG_READ_BULK -> {
                ObjectMapper mapper = new ObjectMapper();

                NotifyBulkReadDto dto = mapper.convertValue(notification.getBody(), NotifyBulkReadDto.class);

                Query query = Query.query(Criteria.where("chatId").is(dto.getChatId())
                        .and("readAt." + dto.getReadBy()).exists(false));

                long count = mongoTemplate.count(query, "messages");
                if (count == 0) { //if there are no unread messages.
                    break;
                }
                BulkReadDto bulkReadDto = messageService.updateReadAtBulk(dto).getBody();
                assert bulkReadDto != null;

                Chat chat = chatService.findById(bulkReadDto.getChatId()).getBody();


                if (chat != null) {
                    notification.setType(NotificationType.MSG_READ_BULK);
                    notification.setBody(bulkReadDto);
                    List<String> members = chat.getMembers();
                    for (String id : members) {
                        messagingTemplate.convertAndSend("/queue/" + id, notification);
                    }
                }
            }

            case GROUP_CREATED -> {
                String chatId = (String) notification.getBody();
                Chat chat = chatService.findById(chatId).getBody();

                if(chat != null) {
                    notification.setType(NotificationType.ADDED_TO_GROUP);
                    notification.setBody(chat);
                    List<String> members = chat.getMembers();
                    for (String id : members) {
                        messagingTemplate.convertAndSend("/queue/" + id, notification);
                    }
                }
            }
        }


    }
}
