package com.epam.hotel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.hotel.dto.HotelAddressDto;
import com.epam.hotel.dto.HotelDto;
import com.epam.hotel.entity.Hotel;
import com.epam.hotel.entity.Room;
import com.epam.hotel.exception.HotelNotFoundException;
import com.epam.hotel.mapper.HotelMapper;
import com.epam.hotel.mapper.HotelMapperImpl;
import com.epam.hotel.repository.HotelRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class HotelServiceTest {

	@Mock
	HotelRepository hotelRepository;

	@InjectMocks
	private HotelService hotelService = new HotelServiceImpl();

	private HotelMapper hotelMapper;

	private Hotel hotel;

	private HotelDto hotelDto;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		hotelMapper = new HotelMapperImpl();
		hotel = new Hotel();
		hotelDto = new HotelDto();
		hotelDto.setIsActive(true);

		List<Room> rooms = new ArrayList<>();
		rooms.add(new Room());
		hotel.setRooms(rooms);
		hotelDto.setHotelName("The Taj");
		hotelDto.setHotelAddress(new HotelAddressDto());
		hotel = hotelMapper.convert(hotelDto);
	}

	@Test
	void addHotelTest() {
		when(hotelRepository.save(ArgumentMatchers.any(Hotel.class))).thenReturn(hotel);
		Hotel hotel = hotelService.addHotel(hotelDto);

		Assertions.assertAll(() -> assertNotNull(hotel),
				() -> assertEquals(hotel.getHotelName(), hotelDto.getHotelName()));

	}

	@Test
	void getHotelsTest() {
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		hotels.add(hotel);
		when(hotelRepository.findAll()).thenReturn(hotels);
		assertEquals("The Taj", hotelService.getHotels().get(0).getHotelName());

	}

	@Test
	void getHotelByIdTest() {
		int id = 1;
		when(hotelRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(hotel));
		assertEquals("The Taj", hotelService.getHotelById(id).getHotelName());
	}

	@Test
	void getHotelByNameTest() {
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		hotels.add(hotel);
		when(hotelRepository.findByHotelName(ArgumentMatchers.anyString())).thenReturn(hotelMapper.convert(hotelDto));
		assertEquals("The Taj", hotelService.getHotelsByName("The Taj").getHotelName());
	}

	@Test()
	void HotelNotFoundExceptionTest() {

		Optional<Hotel> optionalHotel = Optional.of(hotel);
		Mockito.when(hotelRepository.findById(1)).thenReturn(optionalHotel);

		Hotel existingHotel = hotelService.getHotelById(1);
		Mockito.when(hotelRepository.save(existingHotel)).thenReturn(existingHotel);

		Assertions.assertThrows(HotelNotFoundException.class, () -> hotelService.getHotelById(100));
	}

	@Test
	void updateHotelTest() {
		Optional<Hotel> optionalHotel = Optional.of(hotel);
		Mockito.when(hotelRepository.findById(ArgumentMatchers.anyInt())).thenReturn(optionalHotel);

		Hotel existingHotel = hotelService.getHotelById(1);
		Mockito.when(hotelRepository.save(existingHotel)).thenReturn(existingHotel);

		Hotel actualHotel = hotelService.updateHotel(hotelDto, 1);
		Assertions.assertEquals(existingHotel.getHotelName(), actualHotel.getHotelName());
	}

	@Test
	void deleteHotelTest() {
		Optional<Hotel> optionalHotel = Optional.of(hotel);
		Mockito.when(hotelRepository.findById(ArgumentMatchers.anyInt())).thenReturn(optionalHotel);

		Hotel deleted = hotelService.deleteHotel(1);
		Mockito.when(hotelRepository.save(deleted)).thenReturn(deleted);
		Assertions.assertEquals(deleted.getHotelName(), hotel.getHotelName());

	}

}
