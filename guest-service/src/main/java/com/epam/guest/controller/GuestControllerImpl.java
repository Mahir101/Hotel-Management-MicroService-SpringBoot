package com.epam.guest.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.epam.guest.dto.ApiResponse;
import com.epam.guest.dto.UserDto;
import com.epam.guest.entity.User;
import com.epam.guest.mapper.UserMapper;
import com.epam.guest.service.GuestService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class GuestControllerImpl implements GuestController {

	@Autowired
	private GuestService guestService;

	@Autowired
	private UserMapper userMapper;
	@Override
	public ResponseEntity<ApiResponse<UserDto>> addUser(UserDto userDto) {
		log.info("Entered into the " + GuestControllerImpl.class.getName() + "addUser");
		User user = guestService.addUser(userDto);
		return new ResponseEntity<>(new ApiResponse<>(userMapper.convert(user), 
				LocalDate.now(), "User created "), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ApiResponse<List<UserDto>>> getUsers() {
		log.info("Getting the user");
		List<User> users = guestService.getUsers();
		List<UserDto> usersDtos= users.stream().map(user -> userMapper.convert(user))
				.collect(Collectors.toList());

		return new ResponseEntity<>(new ApiResponse<>(usersDtos, 
				LocalDate.now(), "User created "), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<UserDto>> getUserById(int userid) {
		log.info("Entered into the " + GuestControllerImpl.class.getName() + "getUserById");
		User user = guestService.getUserById(userid);
		log.info("User has retrieved by the userid" + userid);
		return new ResponseEntity<>(new ApiResponse<>(userMapper.convert(user),
				LocalDate.now(), "User has retrieved"), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<UserDto>> updateUser(UserDto userDto, int userid) {
		log.info("Enterd into the " + GuestControllerImpl.class.getName() + "updateUser");
		User user = guestService.updateUser(userDto, userid);
		log.info("User has retrieved by the userid" + userid);
		return new ResponseEntity<>(new ApiResponse<>(userMapper.convert(user),
				LocalDate.now(), "User" + userid + "has updated"),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<UserDto>> deleteUserById(int userid) {
		log.info("Entered into the " + GuestControllerImpl.class.getName() + "deleteUserById" + userid);
		return new ResponseEntity<>(new ApiResponse<>(null, LocalDate.now(), guestService.deleteUser(userid)),
				HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<UserDto>> getUserByUserName(String username) {
		log.info("Entered into the " + GuestControllerImpl.class.getName() + "getUserByUserName");
		User user = guestService.getUserByUserName(username);
		return new ResponseEntity<>(new ApiResponse<>(userMapper.convert(user),
				LocalDate.now(), "User with " + username + " has retrieved"),
				HttpStatus.OK);
	}

}
