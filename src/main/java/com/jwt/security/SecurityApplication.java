package com.jwt.security;

import com.jwt.security.model.User;
import com.jwt.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		User user = new User();

		user.setUsername("thai");
		user.setPassword(passwordEncoder.encode("1201"));

		userRepository.save(user);

		System.out.println(user);

	}
}
