package com.dedekorkut.chat.bean.user;

import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.entity.User;
import com.dedekorkut.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ReadUserBean {

    private final UserRepository userRepository;

    public ResponseEntity<User> findById(String id) {
        if (!userRepository.existsById(id)) {
            throw new WillfulException("User with ID " + id + " does not exist.");
        }
        User user = userRepository.findById(id).get();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<List<User>> findAll() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public boolean isUserBlocked(String userId) {
        return userRepository.findByBlockedContains(userId);
    }

    public ResponseEntity<User> findByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new WillfulException("User with username " + username + " does not exist.");
        }
        User user = optionalUser.get();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<String> findUsernameById(String id) {
        User user = findById(id).getBody();
        return new ResponseEntity<>(user.getUsername(), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, String>> findUsernamesByIds(String[] ids) {
        Map<String, String> map = new HashMap<>();
        for (String id : ids) {
            map.put(id, findById(id).getBody().getUsername());
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
