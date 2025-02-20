package ru.kata.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("pageTitle", "Admin Panel");
        return "admin";
    }

    @GetMapping("/edit")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "edit";
    }

    @PostMapping("/add")
    public String create(@RequestParam String username,
                         @RequestParam String password,
                         @RequestParam String firstname,
                         @RequestParam String lastname,
                         @RequestParam String email) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);
        userService.add(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam long id,
                       @RequestParam(required = false) String firstName,
                       @RequestParam(required = false) String lastName,
                       @RequestParam(required = false) String email) {
        User user = new User();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        userService.update(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String username) {
        userService.deleteByUsername(username);
        return "redirect:/admin";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}