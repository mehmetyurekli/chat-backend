package com.dedekorkut.chat.service.impl;

import com.dedekorkut.chat.bean.chat.CreateChatBean;
import com.dedekorkut.chat.bean.chat.DeleteChatBean;
import com.dedekorkut.chat.bean.chat.ReadChatBean;
import com.dedekorkut.chat.bean.chat.UpdateChatBean;
import com.dedekorkut.chat.dto.CreateChatDto;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {
    private final CreateChatBean createChatBean;
    private final ReadChatBean readChatBean;
    private final UpdateChatBean updateChatBean;
    private final DeleteChatBean deleteChatBean;

    @Override
    public ResponseEntity<Chat> create(CreateChatDto createChatDto) {
        return createChatBean.create(createChatDto);
    }

    @Override
    public ResponseEntity<Chat> findById(String id) {
        return readChatBean.findByChatId(id);
    }

    @Override
    public ResponseEntity<List<Chat>> findAll() {
        return readChatBean.findAll();
    }

    @Override
    public ResponseEntity<List<Chat>> findAllByMemberId(String id) {
        return readChatBean.findAllByMemberId(id);
    }

    @Override
    public ResponseEntity<Chat> update(String id, CreateChatDto createChatDto) {
        return updateChatBean.update(id, createChatDto);
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        return deleteChatBean.deleteById(id);
    }
}
