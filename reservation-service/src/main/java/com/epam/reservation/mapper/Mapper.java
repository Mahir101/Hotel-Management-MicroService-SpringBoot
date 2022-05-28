package com.epam.reservation.mapper;

import com.epam.reservation.dto.ReservationDto;
import com.epam.reservation.entity.Reservation;

public interface Mapper {
	public Reservation convert(ReservationDto reservationDto);
}
