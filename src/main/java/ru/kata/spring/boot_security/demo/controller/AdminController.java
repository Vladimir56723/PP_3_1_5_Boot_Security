package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserDetailsServices;

@Controller
public class AdminController {
    public final UserDetailsServices userDetailsServices;

    public AdminController(UserDetailsServices userDetailsServices) {
        this.userDetailsServices = userDetailsServices;
    }

    @GetMapping("/admin/all")
    public String userList(Model model) {
        model.addAttribute("allUsers", userDetailsServices.allUsers());
        // model.addAttribute("all_role", userDetailsServices.getRoles());
        return "admin_all";
    }

    @GetMapping("/admin/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("all_role", userDetailsServices.getRoles());
        return "new_admin";
    }

    @PostMapping("/admin/all")
    public String create(@ModelAttribute("user") User user) {
        userDetailsServices.save(user);
        return "redirect:/admin/all";
    }

    @GetMapping("/admin/all/{id}")
    public String show(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userDetailsServices.get(id));

        return "show_user";
    }

    @GetMapping("/admin/all/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userDetailsServices.get(id));
        model.addAttribute("all_role", userDetailsServices.getRoles());
        return "admin_update";
    }

    @PatchMapping("/admin/all/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userDetailsServices.update(user, id);
        return "redirect:/admin/all";
    }

    @DeleteMapping("admin/all/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userDetailsServices.delete(id);
        return "redirect:/admin/all";
    }

}
