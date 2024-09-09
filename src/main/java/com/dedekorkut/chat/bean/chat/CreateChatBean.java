package com.dedekorkut.chat.bean.chat;

import com.dedekorkut.chat.bean.user.ReadUserBean;
import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.dto.CreateChatDto;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.repository.ChatRepository;
import com.dedekorkut.chat.util.ChatType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CreateChatBean {

    private final ChatRepository chatRepository;
    private final ReadUserBean readUserBean;

    public ResponseEntity<Chat> create(CreateChatDto createChatDto) {

        //group chats must have a name. private chat names are default.
        if (createChatDto.getName() == null && createChatDto.getChatType() == ChatType.GROUP) {
            throw new WillfulException("Chat name can't be empty or null.");
        }

        if (createChatDto.getMembers() == null || createChatDto.getMembers().isEmpty()) {
            throw new WillfulException("Chat members can't be empty or null.");
        }

        if (createChatDto.getChatType() == null) {
            throw new WillfulException("Chat type can't be null.");
        }

        if (createChatDto.getCreatedBy() == null || createChatDto.getCreatedBy().isEmpty()) {
            throw new WillfulException("Creator id can't be null or empty.");
        }

        boolean isCreatorAMember = false;
        for (String id : createChatDto.getMembers()) {
            if (id.equals(createChatDto.getCreatedBy())) {
                isCreatorAMember = true;
            }
            readUserBean.findById(id); //check if user ids are correct.
        }
        if (!isCreatorAMember) {
            readUserBean.findById(createChatDto.getCreatedBy());
            createChatDto.getMembers().add(createChatDto.getCreatedBy());
        }

        if (createChatDto.getChatType() == ChatType.PRIVATE) {
            if (createChatDto.getMembers().size() != 2) {
                throw new WillfulException("Private chat size must be 2.");
            }
            Optional<Chat> existingPrivateChat = chatRepository.findPrivateChat(
                    createChatDto.getMembers().get(0), createChatDto.getMembers().get(1));

            if (existingPrivateChat.isPresent()) {
                return new ResponseEntity<>(existingPrivateChat.get(), HttpStatus.CREATED);
            }
        }

        Chat saved = new Chat();
        saved.setCreatedAt(LocalDateTime.now());
        saved.setChatType(createChatDto.getChatType());
        saved.setCreatedBy(createChatDto.getCreatedBy());
        saved.setMembers(createChatDto.getMembers());

        //set up default name for private chats. (id1-id2)
        if (createChatDto.getChatType() == ChatType.PRIVATE) {
            saved.setName(createChatDto.getMembers().get(0) + "-" + createChatDto.getMembers().get(1));
        } else {
            saved.setName(createChatDto.getName());
        }

        saved = chatRepository.save(saved);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
