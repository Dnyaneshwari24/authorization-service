package com.cognizant.auth.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserNotFound extends RuntimeException{

	private static final long serialVersionUID = 210649836231358204L;
	private final String message;
	
	
	
}
