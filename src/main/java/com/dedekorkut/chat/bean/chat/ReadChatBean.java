package com.dedekorkut.chat.bean.chat;

import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ReadChatBean {

    private final ChatRepository chatRepository;

    public ResponseEntity<Chat> findByChatId(String chatId) {
        if (!chatRepository.existsById(chatId)) {
            throw new WillfulException("Chat with ID " + chatId + " does not exist.");
        }

        Chat chat = chatRepository.findById(chatId).get();

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    public ResponseEntity<List<Chat>> findAll() {
        List<Chat> chats = chatRepository.findAll();
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    public ResponseEntity<List<Chat>> findAllByMemberId(String id) {
        List<Chat> chats = chatRepository.findAllByMembersContaining(id);
        return new ResponseEntity<>(chats, HttpStatus.OK);
    }

    public ResponseEntity<Chat> findPrivateChat(String userId1, String userId2){
        Optional<Chat> optionalChat = chatRepository.findPrivateChat(userId1, userId2);
        if (optionalChat.isEmpty()) {
            throw new WillfulException("Chat between " + userId1 + "-" + userId2 + " does not exist.");
        }
        return new ResponseEntity<>(optionalChat.get(), HttpStatus.OK);
    }
}
