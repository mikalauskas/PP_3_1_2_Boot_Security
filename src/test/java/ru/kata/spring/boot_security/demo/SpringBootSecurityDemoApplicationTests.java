package ru.kata.spring.boot_security.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.User.User;
import ru.kata.spring.boot_security.demo.User.UserRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SpringBootSecurityDemoApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;


	@Test
	void contextLoads() {

		assertNotNull(passwordEncoder);
		assertNotNull(userRepository);

		User user = new User();
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("password"));
		user.setEmail("admin@email.com");
		user.setFirstname("admin");
		user.setLastname("admin");
		Set<String> roles = new HashSet<>();
		roles.add("ROLE_ADMIN");
		user.setRoles(roles);
		userRepository.save(user);

	}

}
