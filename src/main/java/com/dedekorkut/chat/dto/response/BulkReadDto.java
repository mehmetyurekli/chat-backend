package com.dedekorkut.chat.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BulkReadDto {

    private String chatId;
    private String readBy;
    private LocalDateTime readAt;
}
