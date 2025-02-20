package ru.kata.spring.boot_security.demo.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
    <T> List<T> getRolesByUsername(String username);

}
