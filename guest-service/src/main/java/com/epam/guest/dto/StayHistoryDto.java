package com.epam.guest.dto;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StayHistoryDto {
	@JMap
	private int id;
	@JMap
	private int reservationID;
	
	@JMap
	private UserDto userDto;
}
