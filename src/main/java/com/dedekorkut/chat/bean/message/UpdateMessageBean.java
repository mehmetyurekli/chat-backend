package com.dedekorkut.chat.bean.message;

import com.dedekorkut.chat.bean.chat.ReadChatBean;
import com.dedekorkut.chat.bean.user.ReadUserBean;
import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.entity.Chat;
import com.dedekorkut.chat.entity.Message;
import com.dedekorkut.chat.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@Component
public class UpdateMessageBean {

    private final MessageRepository messageRepository;
    private final ReadChatBean readChatBean;
    private final ReadUserBean readUserBean;
    private final MongoTemplate mongoTemplate;

    public HttpStatus updateReadAt(String chatId, String userId) {
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
        return HttpStatus.OK;
    }
}
