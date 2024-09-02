package com.dedekorkut.chat.controller;

import com.dedekorkut.chat.dto.CreateUserDto;
import com.dedekorkut.chat.entity.User;
import com.dedekorkut.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/getUsername")
    public ResponseEntity<String> findUsernameById(@RequestParam String id) {
        return userService.findUsernameById(id);
    }

    @GetMapping("/getUsernames")
    public ResponseEntity<Map<String, String>> findUsernamesByIds(@RequestParam String... ids) {
        return userService.findUsernamesByIds(ids);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody CreateUserDto createUserDto) {
        return userService.create(createUserDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        return userService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user) {
        return userService.update(id, user);
    }
}
