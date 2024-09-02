package com.dedekorkut.chat.dto;

import com.dedekorkut.chat.util.ChatType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateChatDto {

    private String name;
    private List<String> members;
    private ChatType chatType;
    private String createdBy;
}
