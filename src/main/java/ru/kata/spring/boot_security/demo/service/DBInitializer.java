package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;


import javax.annotation.PostConstruct;
import java.util.Set;

@Service
public class DBInitializer {
    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleService roleService;

    public DBInitializer(UserService userService,
                         UserRepository userRepository, RoleService roleService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Transactional
    @PostConstruct
    public void initDB() {
        roleService.save(new Role("ADMIN"));
        roleService.save(new Role("USER"));
        userService.addUser(new User("admin", "admin@test.com", (byte) 45,
                "admin", Set.of(new Role(1L, "ADMIN"))));
        userService.addUser(new User("user", "user@test.com", (byte) 25,
                "user", Set.of(new Role(2L,"USER"))));
    }
}
