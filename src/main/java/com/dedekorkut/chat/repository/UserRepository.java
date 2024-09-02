package com.dedekorkut.chat.repository;

import com.dedekorkut.chat.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    boolean findByBlockedContains(String userId);

    Optional<User> findByUsername(String username);
}
