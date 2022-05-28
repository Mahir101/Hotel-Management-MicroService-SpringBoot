package com.epam.hotel.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.hotel.dto.ApiResponse;
import com.epam.hotel.dto.HotelDto;

@RequestMapping("/v1/api")
public interface HotelController {

	@PostMapping("/hotels")
	public ApiResponse<HotelDto> addHotel(@RequestBody HotelDto hotelDto);

	@GetMapping("/hotels")
	public ApiResponse<List<HotelDto>> getHotels();

	@GetMapping("/hotels/{hotelId}")
	public ApiResponse<HotelDto> getHotelById(@PathVariable int hotelId);
	
	@GetMapping("/hotels/name/{name}")
	public ApiResponse<HotelDto> getHotelByName(@PathVariable String name);

	@PutMapping("/hotels/{hotelId}")
	public  ApiResponse<HotelDto> updateHotel(@RequestBody HotelDto hotelDto, @PathVariable int hotelId);

	@DeleteMapping("/hotels/{hotelId}")
	public ApiResponse<HotelDto> deleteHotel(@PathVariable int hotelId);

}
