package com.epam.gateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.epam.gateway.model.LoginDetails;

public interface GatewayController {
	@PostMapping("/v1/login")
	public String login(@RequestBody LoginDetails loginDetails);
}
