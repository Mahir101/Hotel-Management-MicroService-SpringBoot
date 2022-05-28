package com.epam.gateway.utility;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AUTH-SERVICE")
@LoadBalancerClient(name = "AUTH-SERVICE")
public interface GuestFeignClient {

	@GetMapping("/validate/{token}")
	public Boolean validateToken(@PathVariable String token);
}