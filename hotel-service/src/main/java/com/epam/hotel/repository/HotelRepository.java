package com.epam.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.hotel.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	public Hotel findByHotelName(String hotelName);

}
