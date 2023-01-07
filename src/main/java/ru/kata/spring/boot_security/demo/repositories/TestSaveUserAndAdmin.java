package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserDetailService;
import ru.kata.spring.boot_security.demo.service.UserDetailsServices;


import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestSaveUserAndAdmin {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public TestSaveUserAndAdmin(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void add() {
        Role role1 = new Role(1L, "ROLE_USER");
        Set<Role> role_ad = new HashSet<>();
        role_ad.add(role1);
        roleRepository.save(role1);
        role_ad.add(role1);
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setLastname("LastUser");
        user.setEmail("user@mail");
        user.setId(1L);
        user.setRoles(role_ad);
        userRepository.save(user);


        // userDetailsServices.save(user);
        Role role2 = new Role(2L, "ROLE_ADMIN");
        Set<Role> role_ad1 = new HashSet<>();
        role_ad.add(role2);
        roleRepository.save(role2);
        role_ad.add(role2);
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        user1.setLastname("lastAdmin");
        user1.setEmail("admin@mail");
        user1.setId(2L);
        user1.setRoles(role_ad);
        userRepository.save(user1);

    }


}
