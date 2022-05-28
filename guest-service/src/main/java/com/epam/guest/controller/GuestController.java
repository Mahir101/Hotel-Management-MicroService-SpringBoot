package com.epam.guest.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.guest.dto.ApiResponse;
import com.epam.guest.dto.UserDto;
import com.epam.guest.entity.User;

@RequestMapping("/v1/api")
public interface GuestController {
	@PostMapping("/users")
	public ResponseEntity<ApiResponse<UserDto>> addUser(@RequestBody UserDto userDto);

	@GetMapping("/users")
	public ResponseEntity<ApiResponse<List<UserDto>>> getUsers();

	@GetMapping("/users/{userid}")
	public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable int userid);

	@PutMapping("/users/{userid}")
	public ResponseEntity<ApiResponse<UserDto>> updateUser(@RequestBody UserDto userDto, @PathVariable int userid);

	@DeleteMapping("/users/{userid}")
	public ResponseEntity<ApiResponse<UserDto>> deleteUserById(@PathVariable int userid);

	@GetMapping("/users/username/{username}")
	public ResponseEntity<ApiResponse<UserDto>> getUserByUserName(@PathVariable String username);

}
