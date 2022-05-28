package com.epam.gateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.gateway.filter.AuthFilter;
import com.epam.gateway.filter.AuthFilterConfig;

@Configuration
public class GatewayConfiguration {

	@Autowired
	private AuthFilter authenticationFilter;

	@Bean
	public RouteLocator gateWayRoutes(RouteLocatorBuilder builder) {
		return builder.routes().route("guest-service", r -> r.path("/v1/api/users/**")
				.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig()))).uri("lb://guest-service"))
				.route("hotel-service",
						r -> r.path("/v1/api/hotels/**")
								.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
								.uri("lb://hotel-service"))
				.route("payment-service",
						r -> r.path("/v1/api/payments/**")
								.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
								.uri("lb://payment-service"))
				.route("reservation-service",
						r -> r.path("/v1/api/reservations/**")
								.filters(f -> f.filter(authenticationFilter.apply(new AuthFilterConfig())))
								.uri("lb://reservation-service"))
				.build();

	}
}
