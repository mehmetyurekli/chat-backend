package com.dedekorkut.chat.bean.chat;

import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteChatBean {

    private final ChatRepository chatRepository;

    public ResponseEntity<Void> deleteById(String id) {
        if (!chatRepository.existsById(id)) {
            throw new WillfulException("Chat with ID " + id + " does not exist.");
        }

        chatRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
