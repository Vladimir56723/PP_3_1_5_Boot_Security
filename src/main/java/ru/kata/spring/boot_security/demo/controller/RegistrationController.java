package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.Registration;
import ru.kata.spring.boot_security.demo.service.UserDetailsServices;

@Controller
public class RegistrationController {

    private final UserDetailsServices userDetailsServices;
    private final Registration registration;

    public RegistrationController(UserDetailsServices userDetailsServices, Registration registration) {
        this.userDetailsServices = userDetailsServices;
        this.registration = registration;
    }


    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("all_role", userDetailsServices.getRoles());
        return "registration";
    }

    @PostMapping("/registration")
    public String perform(@ModelAttribute("user") User user) {
        registration.register(user);

        return "login2";
    }

}
