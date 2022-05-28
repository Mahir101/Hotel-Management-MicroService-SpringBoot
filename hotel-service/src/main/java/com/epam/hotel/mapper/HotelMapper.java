package com.epam.hotel.mapper;

import com.epam.hotel.dto.HotelDto;
import com.epam.hotel.entity.Hotel;

public interface HotelMapper {
	
   public Hotel convert(HotelDto userDto);
   
   public HotelDto convert(Hotel hotel);
   
}