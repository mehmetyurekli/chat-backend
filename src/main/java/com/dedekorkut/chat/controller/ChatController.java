package com.dedekorkut.chat.controller;

import com.dedekorkut.chat.dto.CreateChatDto;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;

    @GetMapping
    public ResponseEntity<List<Chat>> findAll() {
        return chatService.findAll();
    }

    @GetMapping("/memberId/{id}")
    public ResponseEntity<List<Chat>> findAllByMemberId(@PathVariable String id) {
        return chatService.findAllByMemberId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> findById(@PathVariable String id) {
        return chatService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Chat> save(@RequestBody CreateChatDto createChatDto) {
        return chatService.create(createChatDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        return chatService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chat> update(@PathVariable String id, @RequestBody CreateChatDto createChatDto) {
        return chatService.update(id, createChatDto);
    }
}
