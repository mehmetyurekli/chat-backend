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
public class DeleteUserBean {
    private final UserRepository userRepository;

    public ResponseEntity<Void> deleteById(String id) {
        if (!userRepository.existsById(id)) {
            throw new WillfulException("Chat with ID " + id + " does not exist.");
        }

        User existingUser = userRepository.findById(id).get();
        existingUser.setDeleted(true);
        userRepository.save(existingUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
