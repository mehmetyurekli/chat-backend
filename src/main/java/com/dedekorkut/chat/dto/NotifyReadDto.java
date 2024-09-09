package com.dedekorkut.chat.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NotifyReadDto {

    private String chatId;
    private String messageId;
    private String readBy;
}
