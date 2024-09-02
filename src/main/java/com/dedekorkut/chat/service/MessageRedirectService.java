package com.dedekorkut.chat.service;

import com.dedekorkut.chat.dto.CreateMessageDto;

public interface MessageRedirectService {

    void redirect(CreateMessageDto messageDto);
}
