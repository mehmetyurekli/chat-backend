package com.dedekorkut.chat.service.impl;

import com.dedekorkut.chat.bean.user.CreateUserBean;
import com.dedekorkut.chat.bean.user.DeleteUserBean;
import com.dedekorkut.chat.bean.user.ReadUserBean;
import com.dedekorkut.chat.bean.user.UpdateUserBean;
import com.dedekorkut.chat.dto.CreateUserDto;
import com.dedekorkut.chat.entity.User;
import com.dedekorkut.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final CreateUserBean createUserBean;
    private final ReadUserBean readUserBean;
    private final UpdateUserBean updateUserBean;
    private final DeleteUserBean deleteUserBean;

    @Override
    public ResponseEntity<User> create(CreateUserDto userDto) {
        return createUserBean.create(userDto);
    }

    @Override
    public ResponseEntity<User> findById(String id) {
        return readUserBean.findById(id);
    }

    @Override
    public ResponseEntity<List<User>> findAll() {
        return readUserBean.findAll();
    }

    @Override
    public ResponseEntity<User> update(String id, User user) {
        return updateUserBean.update(id, user);
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        return deleteUserBean.deleteById(id);
    }

    @Override
    public ResponseEntity<String> findUsernameById(String id) {
        return readUserBean.findUsernameById(id);
    }

    @Override
    public ResponseEntity<Map<String, String>> findUsernamesByIds(String... ids) {
        return readUserBean.findUsernamesByIds(ids);
    }

    @Override
    public ResponseEntity<User> findByUsername(String username) {
        return readUserBean.findByUsername(username);
    }


}
