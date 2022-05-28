package com.epam.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

import com.epam.gateway.model.LoginDetails;
import com.epam.gateway.utility.JwtUtility;

@RestController
public class GatewayControllerImpl implements GatewayController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtility jwtUtility;

	@Override
	public String login(LoginDetails loginDetails) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDetails.getUserName(), loginDetails.getPassword()));

		return jwtUtility.generateToken(loginDetails.getUserName());
	}

}
