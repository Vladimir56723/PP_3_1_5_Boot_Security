package ru.kata.spring.boot_security.demo.service;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.security.MyUserDetails;
import ru.kata.spring.boot_security.demo.security.Registration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServices implements UserDetailService {
    private final PasswordEncoder passwordEncoder;
    private final Registration registration;
    @PersistenceContext
    private EntityManager entityManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserDetailsServices(@Lazy PasswordEncoder passwordEncoder, @Lazy Registration registration, UserRepository userRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.registration = registration;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void update(User user, Long id) {
        User user1 = entityManager.find(User.class, id);
        user1.setFirstname(user.getFirstname());
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        user1.setLastname(user.getLastname());
        user1.setAge(user.getAge());
        user1.setEmail(user.getEmail());
        user1.setRoles(user.getRoles());
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
        registration.register(user);
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
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
