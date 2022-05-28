package com.epam.hotel.dto;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoomDto {
	@JMap
	private int id;
	
	@JMap
	private int roomNumber;
	
	@JMap
	private String roomStatus;
	
	@JMap
	private Boolean isActive;
	
	@JMap
	private RoomTypeDto roomType;
}
