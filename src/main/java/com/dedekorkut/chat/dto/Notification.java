package com.dedekorkut.chat.dto;

import com.dedekorkut.chat.util.NotificationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Notification {
    private NotificationType type;
    private Object body;
}
