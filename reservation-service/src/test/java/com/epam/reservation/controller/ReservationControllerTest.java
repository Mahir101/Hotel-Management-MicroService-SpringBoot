package com.epam.reservation.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.epam.reservation.dto.ReservationDto;
import com.epam.reservation.entity.Reservation;
import com.epam.reservation.mapper.ReservationMapper;
import com.epam.reservation.model.ApiResponse;
import com.epam.reservation.service.ReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ReservationControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	private ReservationMapper reservationMapper;
	private ReservationDto reservationDto;
	@MockBean
	private ReservationService reservationService;

	@BeforeEach
	void beforeEach() {
		reservationMapper = new ReservationMapper();
		reservationDto = new ReservationDto();
		reservationDto.setId(1);
		reservationDto.setIsActive(true);
		reservationDto.setBookingStatus("Booked");
		String checkin = "2021-05-05";
		// Date checkinDate = Date.valueOf(checkin);
		String checkout = "2021-05-10";
		// Date checkoutDate = Date.valueOf(checkout);
		reservationDto.setCheckInDate(checkin);
		reservationDto.setCheckOutDate(checkout);
		reservationDto.setHotelName("ABC");
		reservationDto.setPartyMix(4);
//		reservationDto.setPaymentId(111);
		reservationDto.setTotalCost(10000);
		reservationDto.setUserName("test");
		reservationDto.setRoomType("KING BED");
	}

	@Test
	void addReservationTest() throws Exception {
		String ReservationData = objectMapper.writeValueAsString(reservationDto);
		Reservation res = reservationMapper.convert(reservationDto);
		Mockito.when(reservationService.addReservation(reservationDto)).thenReturn(
				new ResponseEntity<>(new ApiResponse<>(res, new Date(), "reservation created "), HttpStatus.CREATED));
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/reservations")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(ReservationData))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getReservationDetailsTest() throws Exception {
		Mockito.when(reservationService.getReservationDetailsById(ArgumentMatchers.anyInt()))
				.thenReturn(reservationMapper.convert(reservationDto));
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/reservations/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getAllReservationsTest() throws Exception {
		List<Reservation> listOfReservations = new ArrayList<>();
		listOfReservations.add(reservationMapper.convert(reservationDto));
		Mockito.when(reservationService.getAllReservations()).thenReturn(listOfReservations);
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/api/reservations"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void cancelReservationTest() throws Exception {
		String userDtoJson = objectMapper.writeValueAsString(reservationDto);
		Mockito.when(reservationService.cancelReservation(ArgumentMatchers.any(ReservationDto.class),
				ArgumentMatchers.anyInt())).thenReturn(reservationMapper.convert(reservationDto));
		mockMvc.perform(MockMvcRequestBuilders.put("/v1/api/reservations/1").contentType(MediaType.APPLICATION_JSON)
				.content(userDtoJson)).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
