package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;
import ru.kata.spring.boot_security.demo.service.UserDetailsServices;

@Controller
public class UserController {

    public final UserDetailsServices userDetailsServices;

    public UserController(UserDetailsServices userDetailsServices) {
        this.userDetailsServices = userDetailsServices;
    }

    @GetMapping("/user")
    public String show(@AuthenticationPrincipal MyUserDetails user, Model model) {
        model.addAttribute("user", user);
        return "show_user";
    }
}
