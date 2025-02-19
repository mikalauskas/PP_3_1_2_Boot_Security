package ru.kata.spring.boot_security.demo.User;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class UserService {

   @Autowired
   private UserRepository userRepository;

   @Transactional
   public void add(User user) {
      userRepository.save(user);
   }

   @Transactional
   public void update(User user) {
      findById(user.getId()).ifPresent(existingUser -> {
         existingUser.setFirstname(user.getFirstname());
         existingUser.setLastname(user.getLastname());
         existingUser.setEmail(user.getEmail());
      });
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
   public void deleteByUsername(String username) {
      userRepository.deleteByUsername(username);
   }

}
