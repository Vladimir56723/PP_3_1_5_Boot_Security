package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDetailService extends UserDetailsService {
    UserDetails loadUserByUsername(String s);

    User get(Long id);

    void update(User user, Long id);

    List<User> allUsers();

    void save(User user);

    List<Role> getRoles();

    void delete(Long id);
}
