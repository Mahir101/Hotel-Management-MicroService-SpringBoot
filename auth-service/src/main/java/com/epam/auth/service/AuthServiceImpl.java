package com.epam.auth.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.epam.auth.model.ApiResponse;
import com.epam.auth.model.LoginDetails;
import com.epam.auth.model.User;
import com.epam.auth.utility.GuestFeignClient;
import com.epam.auth.utility.JwtUtility;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

	@Autowired
	private GuestFeignClient guestFeignClient;

	@Autowired
	private JwtUtility jwtUtility;

	@Override
	@CircuitBreaker(name = "guest-service", fallbackMethod = "loginFallback")
	public ResponseEntity<ApiResponse<String>> login(LoginDetails loginDetails) {
		log.info("Sending the get call to the guest service to get the user details");
		User user = guestFeignClient.getUserByUserName(loginDetails.getUserName()).getBody().getData();
		log.info("User detials has retrieved ");
		String userName = null;
		if (user != null) {
			if (!user.getPassword().equals(loginDetails.getPassword())) {
				throw new RuntimeException("Password is wrong");
			}
			userName = user.getPassword();
		}

		return new ResponseEntity<>(
				new ApiResponse<>(jwtUtility.generateToken(userName), new Date(), "Token generated"),
				HttpStatus.CREATED);
	}

	@Override
	public Boolean validateToken(String token) {

		return jwtUtility.isTokenExpired(token);
	}

	public ResponseEntity<ApiResponse<String>> loginFallback(LoginDetails loginDetails, Exception e) {

		return new ResponseEntity<>(new ApiResponse<>("guest-service is down", new Date(), e.getMessage()),
				HttpStatus.SERVICE_UNAVAILABLE);
	}

	@Override
	public ResponseEntity<ApiResponse<User>> signUp(User userDto) {
		User user = guestFeignClient.addUser(userDto).getBody().getData();
		return new ResponseEntity<>(new ApiResponse<>(user, new Date(), "Token generated"), HttpStatus.CREATED);
	}

}
