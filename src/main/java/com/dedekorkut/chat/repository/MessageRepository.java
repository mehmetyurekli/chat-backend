package com.dedekorkut.chat.repository;

import com.dedekorkut.chat.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends MongoRepository<Message, String>, PagingAndSortingRepository<Message, String> {
    Page<Message> findAllByChatId(String chatId, Pageable pageable);
}
