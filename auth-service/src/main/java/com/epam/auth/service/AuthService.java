package com.epam.auth.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.auth.model.ApiResponse;
import com.epam.auth.model.LoginDetails;
import com.epam.auth.model.User;

public interface AuthService {

	public ResponseEntity<ApiResponse<User>> signUp(@RequestBody User user);

	public ResponseEntity<ApiResponse<String>> login(LoginDetails loginDetails);

	public Boolean validateToken(String token);
}
