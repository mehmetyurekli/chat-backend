package com.dedekorkut.chat.service;

import com.dedekorkut.chat.dto.CreateUserDto;
import com.dedekorkut.chat.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<User> create(CreateUserDto userDto);

    ResponseEntity<User> findById(String id);

    ResponseEntity<List<User>> findAll();

    ResponseEntity<User> update(String id, User user);

    ResponseEntity<Void> deleteById(String id);

    ResponseEntity<String> findUsernameById(String id);

    ResponseEntity<Map<String, String>> findUsernamesByIds(String... ids);

    ResponseEntity<User> findByUsername(String username);
}
