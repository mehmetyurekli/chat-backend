package com.dedekorkut.chat.service;

import com.dedekorkut.chat.dto.CreateMessageDto;
import com.dedekorkut.chat.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface MessageService {

    ResponseEntity<Message> create(CreateMessageDto messageDto);

    ResponseEntity<Message> findById(String id);

    ResponseEntity<Page<Message>> findAllByChatId(int page, int size, String chatId);

}
