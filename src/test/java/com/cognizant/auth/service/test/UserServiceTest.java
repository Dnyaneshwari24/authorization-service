package com.cognizant.auth.service.test ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import com.cognizant.auth.model.AuthUser;
import com.cognizant.auth.repository.UserRepository;
import com.cognizant.auth.service.UserService;

@SpringBootTest
 class UserServiceTest {

	
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService service;
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	 void loadUserByUserNameShouldUserNameTest() {
		when(userRepository.findByUsername("admin")).thenReturn(new AuthUser(1,"admin","admin"));
		assertThat(service.loadUserByUsername("admin")).isNotNull();
		verify(userRepository, Mockito.times(1)).findByUsername("admin");
	}
	
	

	
}
