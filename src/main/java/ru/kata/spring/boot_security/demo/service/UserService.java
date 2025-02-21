package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class UserService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private RoleRepository roleRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Transactional
   public void add(User user) {
      Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
      userRole.ifPresent(user::setRole);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
   }

   @Transactional(readOnly = true)
   public Optional<User> findById(Long id) {
      return userRepository.findById(id);
   }

   @Transactional(readOnly = true)
   public List<User> listUsers() {
      return userRepository.findAll();
   }

   @Transactional
   public void deleteById(Long id) {
      userRepository.deleteById(id);
   }

   @Transactional
   public void update(User user) {
      userRepository.save(user);
   }

   public User getByUsername(String username) {
      return userRepository.findByUsername(username).orElse(null);
   }
}
