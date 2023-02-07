package com.example.demo.init;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DatabaseInitializer {
    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DatabaseInitializer(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void initialization() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);

        User admin = new User("admin", "admin", "admin", "admin", Set.of(roleAdmin, roleUser));
        userService.saveUser(admin);

        User user = new User("user", "user", "user", "user", Set.of(roleUser));
        userService.saveUser(user);
    }
}
