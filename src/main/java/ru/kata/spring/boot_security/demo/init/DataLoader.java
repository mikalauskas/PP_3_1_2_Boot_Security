package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");

        if (roleAdmin.isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        if (roleUser.isEmpty()) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("password");
        admin.setFirstname("admin");
        admin.setLastname("admin");
        admin.setEmail("admin@admin.com");
        roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        admin.setRole(roleAdmin.orElse(null));
        userService.add(admin);
    }
}
