package ru.kata.spring.boot_security.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.Role.Role;
import ru.kata.spring.boot_security.demo.User.User;
import ru.kata.spring.boot_security.demo.User.UserService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringBootSecurityDemoApplicationTests {


	@Test
	void contextLoads() {

	}

}
