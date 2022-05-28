package com.epam.reservation.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.reservation.dto.ReservationDto;
import com.epam.reservation.entity.Reservation;
import com.epam.reservation.model.ApiResponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RequestMapping("/v1/api")
@ApiModel(description = "Reservation Controller")
public interface ReservationController {

	@PostMapping("/reservations")
	@ApiOperation("To create the reservation")
	public ResponseEntity<ApiResponse<Reservation>> addReservation(
			@RequestBody @ApiParam("Reservation Dto") ReservationDto reservationDto);

	@GetMapping("/reservations/{reservationId}")
	@ApiOperation("Get the reservation by reservation id")
	public ResponseEntity<ApiResponse<Reservation>> getReservationDetailsById(@PathVariable int reservationId);

	@GetMapping("/reservations")
	@ApiOperation("Get the all the reservation")
	public ResponseEntity<List<Reservation>> getAllReservations();

	@PutMapping("/reservations/{reservationId}")
	@ApiOperation("Cancel the reservation")
	public ResponseEntity<ApiResponse<Reservation>> cancelReservation(@RequestBody ReservationDto reservationDto,
			@PathVariable int reservationId);

}
