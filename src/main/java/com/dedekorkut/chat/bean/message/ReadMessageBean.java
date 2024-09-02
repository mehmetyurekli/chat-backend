package com.dedekorkut.chat.bean.message;

import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.entity.Message;
import com.dedekorkut.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReadMessageBean {

    private final MessageRepository messageRepository;

    public ResponseEntity<Message> findById(String id) {
        if (!messageRepository.existsById(id)) {
            throw new WillfulException("Chat with ID " + id + " does not exist.");
        }

        Message message = messageRepository.findById(id).get();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public ResponseEntity<Page<Message>> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("sentAt").descending());
        Page<Message> messages = messageRepository.findAll(pageable);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    public ResponseEntity<Page<Message>> findAllByChatId(int page, int size, String chatId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("sentAt").descending());
        Page<Message> messages = messageRepository.findAllByChatId(chatId, pageable);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
