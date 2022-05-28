package com.epam.hotel.dto;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RoomTypeDto {
	@JMap
	private int id;
	
	@JMap
	private String roomName;
	
	@JMap
	private float rentPerDay;
	
	@JMap
	private int roomCapacity;
	
	@JMap
	private Boolean isActive;
}
