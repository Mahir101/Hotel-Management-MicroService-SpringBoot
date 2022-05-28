package com.epam.hotel.service;

import java.util.List;

import com.epam.hotel.entity.Hotel;
import com.epam.hotel.dto.HotelDto;

public interface HotelService {

	public Hotel addHotel(HotelDto hotelDto);

	public List<Hotel> getHotels();

	public Hotel getHotelById(int hotelId);

	public Hotel updateHotel(HotelDto hotelDto, int hotelId);

	public Hotel deleteHotel(int hotelId);

	public Hotel getHotelsByName(String name);

}
