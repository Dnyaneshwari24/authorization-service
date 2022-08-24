package com.cognizant.auth;

import java.util.List;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.cognizant.auth.model.AuthUser;
import com.cognizant.auth.repository.UserRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
public class AuthorizationServiceApplication {

	
	private UserRepository repository;
	
	
	@Autowired
	public AuthorizationServiceApplication(UserRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	public void initUser() {
		List<AuthUser> users = Stream.of(new AuthUser(101, "Ram", "Ram13"), new AuthUser(102, "Aditya", "Adi12")
				,new AuthUser(103, "Maya", "Mayapass")

		).toList();
		repository.saveAll(users);
	}
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServiceApplication.class, args);
	}

}
