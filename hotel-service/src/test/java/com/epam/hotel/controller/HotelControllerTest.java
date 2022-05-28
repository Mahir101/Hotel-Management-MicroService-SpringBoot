package com.epam.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epam.hotel.dto.HotelAddressDto;
import com.epam.hotel.dto.HotelDto;
import com.epam.hotel.entity.Hotel;
import com.epam.hotel.mapper.HotelMapper;
import com.epam.hotel.service.HotelService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HotelControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private HotelMapper hotelMapper;

	@MockBean
	private HotelService hotelService;

	private HotelDto hotelDto;

	@BeforeEach
	void beforeEach() {
		hotelDto = new HotelDto();
		hotelDto.setHotelAddress(new HotelAddressDto());
		hotelDto.setHotelName("Taj");
		hotelDto.setIsActive(true);
		hotelDto.setRooms(new ArrayList<>());
	}

	@Test
	void addHotelTest() throws Exception {
		String hotelDtoJson = objectMapper.writeValueAsString(hotelDto);

		Mockito.when(hotelService.addHotel(hotelDto)).thenReturn(hotelMapper.convert(hotelDto));

		mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/hotels")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(hotelDtoJson)).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void getHotelByIdTest() throws Exception {
		Mockito.when(hotelService.getHotelById(ArgumentMatchers.anyInt()))
		.thenReturn(hotelMapper.convert(hotelDto));

		mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/hotels/1"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getHotelsByNameTest() throws Exception{

		List<Hotel> hotels = new ArrayList<>();
		hotels.add(hotelMapper.convert(hotelDto));
		
		Mockito.when(hotelService.getHotelsByName(ArgumentMatchers.anyString())).thenReturn(hotelMapper.convert(hotelDto));

		mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/hotels/name/Taj")).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void getHotelsTest() throws Exception{

		List<Hotel> hotels = new ArrayList<>();
		hotels.add(hotelMapper.convert(hotelDto));

		Mockito.when(hotelService.getHotels()).thenReturn(hotels);

		mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/hotels")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void updateHotelTest() throws Exception{

		String hotelDtoJson = objectMapper.writeValueAsString(hotelDto);

		Mockito.when(hotelService.updateHotel(ArgumentMatchers.any(HotelDto.class), ArgumentMatchers.anyInt()))
				.thenReturn(hotelMapper.convert(hotelDto));

		mockMvc.perform(MockMvcRequestBuilders.put("/v1/api/hotels/1").contentType(MediaType.APPLICATION_JSON)
				.content(hotelDtoJson)).andExpect(MockMvcResultMatchers.status().isOk());
	
	}

	@Test
	void deleteHotelTest() throws Exception{

		Mockito.when(hotelService.deleteHotel(ArgumentMatchers.anyInt())).thenReturn(new Hotel());

		mockMvc.perform(MockMvcRequestBuilders.delete("/v1/api/hotels/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	
	}

}

