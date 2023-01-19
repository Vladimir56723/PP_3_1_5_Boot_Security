package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;
import ru.kata.spring.boot_security.demo.service.UserDetailService;

@RestController
public class UserController {
    public final UserDetailService userDetailService;

    public UserController(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @GetMapping("/userPage")
    public ResponseEntity<MyUserDetails> userInfoPage(Authentication a) {
        return new ResponseEntity<>((MyUserDetails) userDetailService.loadUserByUsername(a.getName()), HttpStatus.OK);
    }
}
