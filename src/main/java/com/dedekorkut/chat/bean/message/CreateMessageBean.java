package com.dedekorkut.chat.bean.message;

import com.dedekorkut.chat.bean.chat.CreateChatBean;
import com.dedekorkut.chat.bean.chat.ReadChatBean;
import com.dedekorkut.chat.bean.user.ReadUserBean;
import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.dto.CreateMessageDto;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.entity.Message;
import com.dedekorkut.chat.entity.User;
import com.dedekorkut.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class CreateMessageBean {

    private final MessageRepository messageRepository;
    private final ReadUserBean readUserBean;
    private final ReadChatBean readChatBean;

    public ResponseEntity<Message> create(CreateMessageDto messageDto) {

        //todo: must check if the sender matches the user in security context

        if (messageDto.getFrom() == null || messageDto.getFrom().isEmpty()) {
            throw new WillfulException("Sender information can't be null or empty.");
        }
        User sender = readUserBean.findById(messageDto.getFrom()).getBody();
        assert sender != null;

        if (messageDto.getContent() == null || messageDto.getContent().isEmpty()) {
            throw new WillfulException("Message content can't be null or empty.");
        }

        if (messageDto.getChatId() == null || messageDto.getChatId().isEmpty()) {
            throw new WillfulException("Chat ID can't be null or empty.");
        }

        Chat chat = readChatBean.findByChatId(messageDto.getChatId()).getBody();

        assert chat != null;

        Message savedMessage = new Message();
        savedMessage.setFrom(sender.getId());
        savedMessage.setChatId(chat.getId());
        savedMessage.setContent(messageDto.getContent());
        savedMessage.setSentAt(LocalDateTime.now());
        savedMessage.setReadAt(null);

        savedMessage = messageRepository.save(savedMessage);

        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
    }
}
