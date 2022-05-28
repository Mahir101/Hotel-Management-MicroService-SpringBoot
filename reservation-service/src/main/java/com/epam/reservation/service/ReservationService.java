package com.epam.reservation.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.epam.reservation.dto.ReservationDto;
import com.epam.reservation.entity.Reservation;
import com.epam.reservation.exception.ReservationNotFoundException;
import com.epam.reservation.model.ApiResponse;

public interface ReservationService {

	public ResponseEntity<ApiResponse<Reservation>> addReservation(ReservationDto reservation);

	public Reservation getReservationDetailsById(int anyInt) throws ReservationNotFoundException;

	public List<Reservation> getAllReservations();
	
	public Reservation cancelReservation(ReservationDto reservation,int anyInt);

}
