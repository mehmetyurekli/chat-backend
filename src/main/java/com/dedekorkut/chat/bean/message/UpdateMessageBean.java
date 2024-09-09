package com.dedekorkut.chat.bean.message;

import com.dedekorkut.chat.bean.chat.ReadChatBean;
import com.dedekorkut.chat.bean.user.ReadUserBean;
import com.dedekorkut.chat.dto.NotifyBulkReadDto;
import com.dedekorkut.chat.dto.NotifyReadDto;
import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.dto.response.BulkReadDto;
import com.dedekorkut.chat.dto.response.ReadDto;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.entity.Message;
import com.dedekorkut.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Component
public class UpdateMessageBean {

    private final MessageRepository messageRepository;
    private final ReadChatBean readChatBean;
    private final ReadUserBean readUserBean;
    private final MongoTemplate mongoTemplate;
    private final ReadMessageBean readMessageBean;

    @Transactional
    public ResponseEntity<BulkReadDto> updateReadAtMulti(NotifyBulkReadDto notifyBulkReadDto) {
        String chatId = notifyBulkReadDto.getChatId();
        String userId = notifyBulkReadDto.getReadBy();

        Chat chat = readChatBean.findByChatId(chatId).getBody();
        readUserBean.findById(userId);

        assert chat != null;
        if(!chat.getMembers().contains(userId)){
           throw new WillfulException("User is not a member of the chat!");
        }

        Update update = new Update();

        update.set("readAt." + userId, LocalDateTime.now());

        Query query = Query.query(Criteria.where("chatId").is(chatId)
                .and("readAt." + userId).exists(false));

        mongoTemplate.updateMulti(query, update, Message.class);

        BulkReadDto bulkReadDto = new BulkReadDto();
        bulkReadDto.setChatId(chatId);
        bulkReadDto.setReadBy(userId);
        bulkReadDto.setReadAt(LocalDateTime.now());
        return new ResponseEntity<>(bulkReadDto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<ReadDto> updateReadAtSingle(NotifyReadDto msgRead){
        Chat chat = readChatBean.findByChatId(msgRead.getChatId()).getBody();
        readUserBean.findById(msgRead.getReadBy());
        readMessageBean.findById(msgRead.getMessageId()).getBody();

        assert chat != null;
        if(!chat.getMembers().contains(msgRead.getReadBy())){
            throw new WillfulException("User is not a member of the chat!");
        }

        Update update = new Update();

        update.set("readAt." + msgRead.getReadBy(), LocalDateTime.now());

        Query query = Query.query(Criteria.where("_id").is(msgRead.getMessageId())
                        .and("from").ne(msgRead.getReadBy())
                .and("readAt." + msgRead.getReadBy()).exists(false));

        mongoTemplate.updateMulti(query, update, Message.class);

        ReadDto readDto = new ReadDto();
        readDto.setMessageId(msgRead.getMessageId());
        readDto.setReadAt(LocalDateTime.now());
        readDto.setChatId(msgRead.getChatId());
        readDto.setReadBy(msgRead.getReadBy());

        return new ResponseEntity<>(readDto, HttpStatus.OK);
    }
}
