package com.cognizant.auth;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cognizant.auth.model.AuthUser;
import com.cognizant.auth.repository.UserRepository;

@SpringBootTest
class AuthorizationServiceApplicationTests {

	@Mock
	private UserRepository userRepository;
	
	@Test
	void saveUser() {
		List<AuthUser> users = Stream.of(new AuthUser(101, "Ram", "mypass"), new AuthUser(102, "Aditya", "pass")
				,new AuthUser(103, "Maya", "Mayapass")

		).toList();
		userRepository.saveAll(users);
		assertNotNull(userRepository);
	}

}
