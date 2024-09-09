package com.dedekorkut.chat.service;

import com.dedekorkut.chat.dto.CreateMessageDto;
import com.dedekorkut.chat.dto.NotifyBulkReadDto;
import com.dedekorkut.chat.dto.NotifyReadDto;
import com.dedekorkut.chat.dto.response.BulkReadDto;
import com.dedekorkut.chat.dto.response.ReadDto;
import com.dedekorkut.chat.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface MessageService {

    ResponseEntity<Message> create(CreateMessageDto messageDto);

    ResponseEntity<Message> findById(String id);

    ResponseEntity<Page<Message>> findAllByChatId(int page, int size, String chatId);

    ResponseEntity<BulkReadDto> updateReadAtBulk(NotifyBulkReadDto notifyBulkReadDto);

    ResponseEntity<ReadDto> updateReadAtSingle(NotifyReadDto msgRead);
}
