package com.epam.gateway.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.gateway.model.ApiResponse;
import com.epam.gateway.model.LoginDetails;
import com.epam.gateway.model.User;

public interface GatewayService {
	public ResponseEntity<ApiResponse<User>> signUp(@RequestBody User user);

	public ResponseEntity<ApiResponse<String>> login(LoginDetails loginDetails);

}
