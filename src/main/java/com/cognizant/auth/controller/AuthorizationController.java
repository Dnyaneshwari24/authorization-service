package com.cognizant.auth.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.auth.exception.UserNotFound;
import com.cognizant.auth.model.AuthRequest;
import com.cognizant.auth.service.UserService;
import com.cognizant.auth.util.JwtUtil;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
public class AuthorizationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationController.class);

	private JwtUtil jwtUtil;

	private UserService userService;

	private AuthenticationManager authenticationManager;

	
	@Autowired
	public AuthorizationController(JwtUtil jwtUtil, UserService userService,
			AuthenticationManager authenticationManager) {

		this.jwtUtil = jwtUtil;
		this.userService = userService;
		this.authenticationManager = authenticationManager;
	}
	//starting message 
	
		@GetMapping("/")
		public ResponseEntity<String> welcome() {
			LOGGER.info("STARTED authorization microservice");
			LOGGER.info("END - authorization microservice welcome");
			return ResponseEntity.ok("Wecome to Authorization application");
		}
		
		//jwt token authentication using user name and password
		
		@PostMapping("/authenticate")
		public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) {
			LOGGER.info("STARTED - generateToken");
			try {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			} catch (Exception e) {
				LOGGER.error("EXCEPTION - generateToken");
				throw new UserNotFound("user not found");
			}

			LOGGER.info("END - generateToken");
			return ResponseEntity.ok(jwtUtil.generateToken(authRequest.getUsername()));
		}
		
		//validation of the generated jwt token to access '/authorize' endpoint

		@GetMapping("/authorize")
		public ResponseEntity<Object> authorization(@RequestHeader(value="Authorization") String token1) {

			LOGGER.info("STARTED - authorization");
			System.out.println(token1);
			String token = token1.substring(7);

			UserDetails user = userService.loadUserByUsername(jwtUtil.extractUsername(token));

			if (jwtUtil.validateToken(token, user)) {
				LOGGER.info("END - authorization");
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else {
				LOGGER.info("END - authorization");
				return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
			}

		}
}
