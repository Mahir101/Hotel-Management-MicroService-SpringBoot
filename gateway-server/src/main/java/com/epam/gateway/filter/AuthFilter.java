package com.epam.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.epam.guest.exception.AuthorizationException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilterConfig> {

	@Autowired
	private RouterValidator routerValidator;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public GatewayFilter apply(AuthFilterConfig config) {

		return (exchange, chain) -> {
			final ServerHttpRequest request = exchange.getRequest();
			ServerHttpRequest modifiedRequest = request;
			if (routerValidator.isSecured.test(request)) {
				final boolean authorization = request.getHeaders().containsKey("Authorization");

				if (!authorization)
					throw new AuthorizationException("Authorization key is missing");
				log.info("Authorization key is there ");
				final String token = request.getHeaders().get("Authorization").get(0);
				if (token.isEmpty()
						&& restTemplate.getForObject("localhost:8083/v1/api/validate/" + token, Boolean.class))
					throw new AuthorizationException("Invalid Token KEY");
				log.info("token is validated ");
				try {
					modifiedRequest = exchange.getRequest().mutate().header("Authorization", token).build();
				} catch (Exception e) {
					throw new AuthorizationException("Modified Request ");
				}
			}
			return chain.filter(exchange.mutate().request(modifiedRequest).build());

		};
	}
}
