package com.epam.hotel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.hotel.dto.HotelDto;
import com.epam.hotel.entity.Hotel;
import com.epam.hotel.exception.HotelNotFoundException;
import com.epam.hotel.mapper.HotelMapperImpl;
import com.epam.hotel.repository.HotelRepository;
@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	

	@Override
	public Hotel addHotel(HotelDto hotelDto) {
		Hotel hotel = new HotelMapperImpl().convert(hotelDto);
		Hotel newHotel = hotelRepository.save(hotel);
		return newHotel;
	}

	@Override
	public List<Hotel> getHotels() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getHotelById(int hotelId) {
		Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
		if (!optionalHotel.isPresent()) {
			throw new HotelNotFoundException("Hotel not found.");
		}
		return optionalHotel.get();
	}

	@Override
	public Hotel updateHotel(HotelDto hotelDto, int hotelId) {
		Hotel updatedHotel = new HotelMapperImpl().convert(hotelDto);
		Hotel existingHotel = getHotelById(hotelId);
		existingHotel.setHotelAddress(updatedHotel.getHotelAddress());
		existingHotel.setRooms(updatedHotel.getRooms());
		existingHotel.setIsActive(true);
		existingHotel.setHotelName(updatedHotel.getHotelName());
		return hotelRepository.save(existingHotel);
	}

	@Override
	public Hotel deleteHotel(int hotelId) {
		Hotel hotel = getHotelById(hotelId);
		hotelRepository.deleteById(hotelId);
		return hotel;
	}

	@Override
	public Hotel getHotelsByName(String name) {
		Hotel hotel = hotelRepository.findByHotelName(name);
		if (hotel != null) {
			return hotel;
			
		}else {
			throw new HotelNotFoundException("Hotel not found.");
		}
		
	}

}
