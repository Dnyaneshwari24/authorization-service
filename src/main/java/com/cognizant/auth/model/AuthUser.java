package com.cognizant.auth.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class AuthUser {

	@Id
	private int id;
	
	private String username;
	
	private String password;

	

	
	
	

}
