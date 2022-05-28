package com.epam.reservation.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

	private int id;
	private String hotelName;
	private HotelAddress hotelAddress;
	private Boolean isActive;
	private List<Room> rooms;
}
