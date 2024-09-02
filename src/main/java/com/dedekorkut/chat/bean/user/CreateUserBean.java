package com.dedekorkut.chat.bean.user;

import com.dedekorkut.chat.common.WillfulException;
import com.dedekorkut.chat.dto.CreateUserDto;
import com.dedekorkut.chat.entity.User;
import com.dedekorkut.chat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CreateUserBean {

    private final UserRepository userRepository;

    public ResponseEntity<User> create(CreateUserDto userDto) {

        if (userDto.getName() == null || userDto.getName().isEmpty() ||
                userDto.getSurname() == null || userDto.getSurname().isEmpty() ||
                userDto.getUsername() == null || userDto.getUsername().isEmpty()) {
            throw new WillfulException("Enter a name, surname and username.");
        }

        User saved = new User();
        saved.setName(userDto.getName());
        saved.setSurname(userDto.getSurname());
        saved.setUsername(userDto.getUsername());
        saved.setDeleted(false);

        saved = userRepository.save(saved);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
