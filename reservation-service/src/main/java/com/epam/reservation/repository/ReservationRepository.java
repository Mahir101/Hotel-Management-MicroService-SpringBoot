package com.epam.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.reservation.entity.Reservation;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

}

