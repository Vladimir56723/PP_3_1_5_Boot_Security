package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestSaveUserAndAdmin {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public TestSaveUserAndAdmin(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void add() {
        Role role2 = new Role(1L, "ROLE_ADMIN");
        roleRepository.save(role2);
        Set<Role> role_ad = new HashSet<>();
        role_ad.add(role2);
        //role_ad.add(role2);
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword(passwordEncoder.encode("admin"));
        user1.setLastname("lastAdmin");
        user1.setAge(50);
        user1.setEmail("admin@mail");
        user1.setId(1L);
        user1.setRoles(role_ad);
        userRepository.save(user1);

        Role role1 = new Role(2L, "ROLE_USER");
        roleRepository.save(role1);
        Set<Role> role_ad2 = new HashSet<>();
        role_ad2.add(role1);
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user"));
        user.setLastname("LastUser");
        user.setAge(55);
        user.setEmail("user@mail");
        user.setId(2L);
        user.setRoles(role_ad2);
        userRepository.save(user);


        Set<Role> role_all = new HashSet<>();
        role_all.add(role2);
        role_all.add(role1);
        User user3 = new User();
        user3.setUsername("u");
        user3.setPassword(passwordEncoder.encode("u"));
        user3.setLastname("Lu");
        user3.setAge(60);
        user3.setEmail("u@mail");
        user3.setId(3L);
        user3.setRoles(role_all);
        userRepository.save(user3);


    }


}
