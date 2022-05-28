package com.epam.hotel.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.epam.hotel.dto.ApiResponse;
import com.epam.hotel.dto.HotelDto;
import com.epam.hotel.entity.Hotel;
import com.epam.hotel.mapper.HotelMapper;
import com.epam.hotel.service.HotelService;

@RestController
public class HotelControllerImpl implements HotelController {


	private static final String SUCCESS = "Success";
	@Autowired
	HotelService hotelService;

	@Autowired
	HotelMapper hotelMapper;

	@Override
	public ApiResponse<HotelDto> addHotel(HotelDto hotelDto) {
		return new ApiResponse<>(hotelMapper.convert(hotelService.addHotel(hotelDto)),
				LocalDate.now(),"Hotel added into DB.");
	}

	@Override
	public ApiResponse<List<HotelDto>> getHotels() {
		List<Hotel> hotels = hotelService.getHotels();
		
		List<HotelDto> hotelDtos = hotels.stream()
				.map(hotel -> hotelMapper.convert(hotel))
				.collect(Collectors.toList());
		
		return new ApiResponse<>(hotelDtos,
				LocalDate.now(),SUCCESS);
	}

	@Override
	public ApiResponse<HotelDto> getHotelById(int hotelId) {
		return new ApiResponse<>(hotelMapper.convert(hotelService.getHotelById(hotelId)),
				LocalDate.now(),SUCCESS);
	}

	@Override
	public ApiResponse<HotelDto> updateHotel(HotelDto hotelDto, int hotelId) {
		return new ApiResponse<>(hotelMapper.convert(hotelService.updateHotel(hotelDto, hotelId)),
				LocalDate.now(),"Hotel details updated successfully.");
	}

	@Override
	public ApiResponse<HotelDto> deleteHotel(int hotelId) {
		return new ApiResponse<>(hotelMapper.convert(hotelService.deleteHotel(hotelId)),
				LocalDate.now(),"Hotel deleted successfully.");
	}

	@Override
	public ApiResponse<HotelDto> getHotelByName(String name) {
		return new ApiResponse<>(hotelMapper.convert(hotelService.getHotelsByName(name)),
				LocalDate.now(),SUCCESS);
	}

}
