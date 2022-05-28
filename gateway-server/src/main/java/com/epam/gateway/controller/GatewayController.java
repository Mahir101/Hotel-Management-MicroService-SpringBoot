package com.epam.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.gateway.model.ApiResponse;
import com.epam.gateway.model.LoginDetails;
import com.epam.gateway.model.User;

@RequestMapping("/v1/api")
public interface GatewayController {
	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<User>> signUp(@RequestBody User user);

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDetails loginDetails);

}
