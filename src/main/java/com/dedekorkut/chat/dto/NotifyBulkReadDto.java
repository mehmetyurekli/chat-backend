package com.dedekorkut.chat.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyBulkReadDto {

    private String chatId;
    private String readBy;
}
