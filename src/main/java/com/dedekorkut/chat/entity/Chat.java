package com.dedekorkut.chat.entity;

import com.dedekorkut.chat.util.ChatType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "chats")
public class Chat {

    @Id
    private String id;

    private String name;
    private List<String> members;
    private LocalDateTime createdAt;
    private String createdBy;

    private ChatType chatType;
}
