package com.dedekorkut.chat.service;

import com.dedekorkut.chat.dto.CreateChatDto;
import com.dedekorkut.chat.entity.Chat;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChatService {

    ResponseEntity<Chat> create(CreateChatDto createChatDto);

    ResponseEntity<Chat> findById(String id);

    ResponseEntity<List<Chat>> findAll();

    ResponseEntity<List<Chat>> findAllByMemberId(String id);

    ResponseEntity<Chat> update(String id, CreateChatDto createChatDto);

    ResponseEntity<Void> deleteById(String id);
}
