package com.epam.guest.dto;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginDetails {
	@JMap
	private int id;
	
	@JMap
	private String userName;
	
	@JMap
	private String password;
}
