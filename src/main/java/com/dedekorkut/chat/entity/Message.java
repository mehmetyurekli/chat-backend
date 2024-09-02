package com.dedekorkut.chat.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "messages")
public class Message {

    @Id
    private String id;

    private String from;
    private String chatId; //could be a group chat or private chat
    private String content;
    private LocalDateTime sentAt;
    private LocalDateTime readAt;
}
