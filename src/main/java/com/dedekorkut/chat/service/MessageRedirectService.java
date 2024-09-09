package com.dedekorkut.chat.service;

import com.dedekorkut.chat.dto.Notification;

public interface MessageRedirectService {

    void redirect(Notification notification);
}
