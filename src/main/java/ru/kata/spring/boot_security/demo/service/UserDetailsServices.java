package ru.kata.spring.boot_security.demo.service;

import org.hibernate.Hibernate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServices implements UserDetailService {
    @PersistenceContext
    private EntityManager entityManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDetailsServices(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void update(User user, Long id) {
        // User user1 = userRepository.getById(id);
        User user1 = entityManager.find(User.class, id);
        user1.setUsername(user.getUsername());
        user1.setPassword(user.getPassword());
        user1.setLastname(user.getLastname());
        user1.setEmail(user.getEmail());
        user1.setRoles(user.getRoles());
        //userRepository.save(user1);
        entityManager.merge(user1);
    }

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public User get(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException("User not found id");
        }

    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        Set<Role> roleSet = user.getRoles();
        Hibernate.initialize(roleSet);
        return new MyUserDetails(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
