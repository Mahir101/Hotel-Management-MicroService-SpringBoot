package com.epam.hotel.dto;

import java.util.List;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class HotelDto {
	@JMap
	private int id;
	
	@JMap
	private String hotelName;
	
	@JMap
	private HotelAddressDto hotelAddress;
	
	@JMap
	private Boolean isActive;
	
	@JMap
	private List<RoomDto> rooms;
}
