package com.cognizant.auth.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.auth.exception.UserNotFound;
import com.cognizant.auth.model.*;
import com.cognizant.auth.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	/*loadbyUserName function loads user from the repository 
	 * returns UserDetails 
	 */

	
	//loading user name from user database passing to spring provided UserDetails  
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
		LOGGER.info("STARTED - loadUserByUsername");
		AuthUser authUser = userRepository.findByUsername(username);
		LOGGER.info("END - loadUserByUsername");
		return new org.springframework.security.core.userdetails.User(authUser.getUsername(), authUser.getPassword(),
				new ArrayList<>());
		
		}catch(Exception e)
		{
			LOGGER.error("ERROR-username not found");
			throw new UserNotFound("User of the given username not found");
		}
		
	}

}
