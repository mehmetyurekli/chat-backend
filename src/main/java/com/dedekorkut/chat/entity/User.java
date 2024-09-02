package com.dedekorkut.chat.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String name;
    private String surname;

    @Indexed(unique = true)
    private String username;

    private Boolean deleted;
    private List<String> blocked;
    private List<String> chats;
}
