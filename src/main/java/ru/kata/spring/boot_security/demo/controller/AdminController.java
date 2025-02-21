package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("pageTitle", "Admin Panel");
        return "admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("pageTitle", "New User");
        return "new_user";
    }

    @PostMapping("/add")
    public String create(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String firstname,
                         @RequestParam String lastname,
                         @RequestParam String email,
                         @RequestParam List<String> roles) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        Set<Role> roleSet = roleRepository.findByNameIn(roles);
        user.setRoles(roleSet);
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam long id,
                       @RequestParam(required = false) String username,
                       @RequestParam(required = false) String firstname,
                       @RequestParam(required = false) String lastname,
                       @RequestParam(required = false) String email,
                       @RequestParam(required = false) String password,
                       @RequestParam(required = false) List<String> roles) {


        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setUsername(username);
            user1.setFirstname(firstname);
            user1.setLastname(lastname);
            user1.setEmail(email);
            user1.setPassword(password);

            Set<Role> roleSet = roleRepository.findByNameIn(roles);
            user1.setRoles(roleSet);
            userService.update(user1);
        }
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    @ModelAttribute("currentUser")
    public UserDetails addCurrentUserToModel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    @ModelAttribute("roles")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}