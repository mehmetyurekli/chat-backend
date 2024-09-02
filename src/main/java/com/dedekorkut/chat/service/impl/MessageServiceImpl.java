package com.dedekorkut.chat.service.impl;

import com.dedekorkut.chat.bean.message.CreateMessageBean;
import com.dedekorkut.chat.bean.message.ReadMessageBean;
import com.dedekorkut.chat.dto.CreateMessageDto;
import com.dedekorkut.chat.entity.Message;
import com.dedekorkut.chat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final CreateMessageBean createMessageBean;
    private final ReadMessageBean readMessageBean;

    @Override
    public ResponseEntity<Message> create(CreateMessageDto messageDto) {
        return createMessageBean.create(messageDto);
    }

    @Override
    public ResponseEntity<Message> findById(String id) {
        return readMessageBean.findById(id);
    }

    @Override
    public ResponseEntity<Page<Message>> findAllByChatId(int page, int size, String chatId) {
        return readMessageBean.findAllByChatId(page, size, chatId);
    }
}
