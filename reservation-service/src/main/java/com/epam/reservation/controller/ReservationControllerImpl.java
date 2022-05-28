package com.epam.reservation.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.epam.reservation.dto.ReservationDto;
import com.epam.reservation.entity.Reservation;
import com.epam.reservation.model.ApiResponse;
import com.epam.reservation.service.ReservationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ReservationControllerImpl implements ReservationController {

	@Autowired
	private ReservationService reservationService;

	@Override
	public ResponseEntity<ApiResponse<Reservation>> addReservation(ReservationDto reservationDto) {
		log.info("Entered"+getClass().getName());
		return reservationService.addReservation(reservationDto);
	}

	@Override
	public ResponseEntity<ApiResponse<Reservation>> getReservationDetailsById(int reservationId) {
		Reservation reservation = reservationService.getReservationDetailsById(reservationId);
		return new ResponseEntity<>(new ApiResponse<>(reservation, new Date(), "reservation Details"),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ApiResponse<Reservation>> cancelReservation(ReservationDto reservationDto, int reservationId) {
		Reservation reservation = reservationService.cancelReservation(reservationDto, reservationId);
		return new ResponseEntity<>(new ApiResponse<>(reservation, new Date(), "reservation Details"),HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<Reservation>> getAllReservations() {
		return new ResponseEntity<>(reservationService.getAllReservations(),HttpStatus.OK);
	}

}
