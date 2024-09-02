package com.dedekorkut.chat.controller;

import com.dedekorkut.chat.dto.CreateMessageDto;
import com.dedekorkut.chat.entity.Message;
import com.dedekorkut.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<Page<Message>> findAll(@RequestParam int page, @RequestParam int size,
                                                 @PathVariable String chatId) {
        return messageService.findAllByChatId(page, size, chatId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable String id) {
        return messageService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Message> save(@RequestBody CreateMessageDto createMessageDto) {
        return messageService.create(createMessageDto);
    }
}
