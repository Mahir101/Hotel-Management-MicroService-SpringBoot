package com.epam.hotel.dto;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HotelAddressDto {
	
	@JMap
	private int id;
	
	@JMap
	private String street;
	
	@JMap
	private String city;
	
	@JMap
	private String state;
	
	@JMap
	private int pinCode;
	
	@JMap
	private String country;
}
