package com.dedekorkut.chat.repository;

import com.dedekorkut.chat.entity.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String>, PagingAndSortingRepository<Chat, String> {

    List<Chat> findAllByMembersContaining(String userId);

    @Query("{chatType: 'PRIVATE', 'members': { $all: [?0, ?1] } }")
    Optional<Chat> findPrivateChat(String userId1, String userId2);
}
