package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;
import ru.kata.spring.boot_security.demo.service.UserDetailsServices;

@RequestMapping("/admin")
@Controller
public class AdminController {
    public final UserDetailsServices userDetailsServices;

    public AdminController(UserDetailsServices userDetailsServices) {
        this.userDetailsServices = userDetailsServices;
    }

    @GetMapping("/all")
    public String userList(@AuthenticationPrincipal MyUserDetails user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userDetailsServices.allUsers());
        model.addAttribute("all_role", userDetailsServices.getRoles());
        return "admin_all";
    }

    @GetMapping("all/user/12")
    public String show12(@AuthenticationPrincipal MyUserDetails user, Model model) {
        model.addAttribute("user", user);
        return "show12";
    }

    @GetMapping("/new")
    public String newUser(@AuthenticationPrincipal MyUserDetails user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userDetailsServices.allUsers());
        model.addAttribute("all_role", userDetailsServices.getRoles());
        model.addAttribute("user1", new User());
        return "newAdmin12";
    }

    @PostMapping("all12")
    public String create(@ModelAttribute("user") User user) {
        userDetailsServices.save(user);
        return "redirect:/admin/all";
    }

    @PatchMapping(value = "/all/edit/{id}")
    public String update(@ModelAttribute User user, @PathVariable("id") Long id) {
        userDetailsServices.update(user, id);
        return "redirect:/admin/all";
    }

    @DeleteMapping("/all/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userDetailsServices.delete(id);
        return "redirect:/admin/all";
    }
}
