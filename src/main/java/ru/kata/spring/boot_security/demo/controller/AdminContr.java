package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;
import ru.kata.spring.boot_security.demo.service.UserDetailService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminContr {
    public final UserDetailService userDetailService;

    public AdminContr(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = userDetailService.allUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/user")
    public ResponseEntity<MyUserDetails> userInfo(Authentication a) {
        return new ResponseEntity<>((MyUserDetails) userDetailService.loadUserByUsername(a.getName()), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public User userId(@PathVariable("id") Long id) {
        return userDetailService.get(id);
    }

    @PostMapping("/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userDetailService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        userDetailService.update(user, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userDetailService.delete(id);
        return ResponseEntity.ok().build();
    }
}
