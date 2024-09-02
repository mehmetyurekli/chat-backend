package com.dedekorkut.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMessageDto {

    private String from;
    private String content;
    private String chatId;

}
