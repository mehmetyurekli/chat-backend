package com.dedekorkut.chat.bean.chat;

import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.dto.CreateChatDto;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateChatBean {

    private final ChatRepository chatRepository;

    public ResponseEntity<Chat> update(String id, CreateChatDto createChatDto) {
        if (!chatRepository.existsById(id)) {
            throw new WillfulException("Chat with ID " + id + " does not exist.");
        }

        Chat existingChat = chatRepository.findById(id).get();

        if (createChatDto.getName() != null && !createChatDto.getName().isEmpty()) {
            existingChat.setName(createChatDto.getName());
        }

        if (createChatDto.getMembers() != null) {
            existingChat.setMembers(createChatDto.getMembers());
        }

        if (createChatDto.getChatType() != null) {
            existingChat.setChatType(createChatDto.getChatType());
        }

        existingChat = chatRepository.save(existingChat);
        return new ResponseEntity<>(existingChat, HttpStatus.OK);
    }
}
