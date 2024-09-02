package com.dedekorkut.chat.bean.user;

import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.entity.User;
import com.dedekorkut.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UpdateUserBean {

    private final UserRepository userRepository;

    public ResponseEntity<User> update(String id, User user) {
        if (!userRepository.existsById(id)) {
            throw new WillfulException("User with ID " + id + " does not exist.");
        }

        User existingUser = userRepository.findById(id).get();

        if (user.getName() != null && !user.getName().isEmpty()) {
            existingUser.setName(user.getName());
        }
        if (user.getSurname() != null && !user.getSurname().isEmpty()) {
            existingUser.setName(user.getName());
        }
        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            existingUser.setName(user.getName());
        }
        if (user.getDeleted() != null) {
            existingUser.setDeleted(user.getDeleted());
        }

        existingUser = userRepository.save(existingUser);
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }
}
