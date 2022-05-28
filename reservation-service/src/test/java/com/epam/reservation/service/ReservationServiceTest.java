package com.epam.reservation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.epam.reservation.dto.ReservationDto;
import com.epam.reservation.entity.Reservation;
import com.epam.reservation.exception.ReservationNotFoundException;
import com.epam.reservation.feing.client.GuestFeignClient;
import com.epam.reservation.feing.client.HotelFeignClient;
import com.epam.reservation.feing.client.PaymentFeignClient;
import com.epam.reservation.mapper.ReservationMapper;
import com.epam.reservation.model.ApiResponse;
import com.epam.reservation.model.CreditCard;
import com.epam.reservation.model.Hotel;
import com.epam.reservation.model.HotelAddress;
import com.epam.reservation.model.Payment;
import com.epam.reservation.model.Profile;
import com.epam.reservation.model.Room;
import com.epam.reservation.model.RoomType;
import com.epam.reservation.model.User;
import com.epam.reservation.repository.ReservationRepository;

class ReservationServiceTest {

	@Mock
	private ReservationRepository reservationRepository;

	@Mock
	GuestFeignClient guestFeignClient;
	@Mock
	HotelFeignClient hotelFeignClient;
	@Mock
	PaymentFeignClient paymentFeignClient;

	@InjectMocks
	private ReservationServiceImpl reservationService;

	private ReservationMapper reservationMapper;
	private Reservation reservation;
	private ReservationDto reservationDto;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		reservationMapper = new ReservationMapper();
		reservationDto = new ReservationDto();
		reservationDto.setId(1);
		reservationDto.setIsActive(true);
		reservationDto.setBookingStatus("Booked");
		String checkin = "2021-05-05";
		String checkout = "2021-05-10";
		reservationDto.setCheckInDate(checkin);
		reservationDto.setCheckOutDate(checkout);
		reservationDto.setHotelName("ABC");
		reservationDto.setPartyMix(4);
		reservationDto.setTotalCost(10000);
		reservationDto.setUserName("test");
		reservationDto.setPayment(new Payment(1, "Credit card", "Success"));
		reservation = reservationMapper.convert(reservationDto);

	}

	@Test
	void addReservationTest() {
		User user = new User();
		user.setStatus(true);
		user.setUserName("Mahir");
		user.setPassword("test");
		List<CreditCard> creditCards = new ArrayList<>();
		creditCards.add(new CreditCard(1, 1234567890, "12/24", "Mahir", "Visa"));
		user.setCreditCards(creditCards);
		user.setProfile(new Profile(1, "Mahir", "Labib", "mahir@gmail.com", 01712232327, "Data",
				"Gulshan", "Dhaka", 1212, "Bangladesh"));
		ResponseEntity<ApiResponse<User>> resUser = new ResponseEntity<ApiResponse<User>>(
				new ApiResponse<>(user, new java.util.Date(), ""), HttpStatus.OK);

		Hotel hotel = new Hotel();
		List<Room> rooms = new ArrayList<>();
		rooms.add(new Room(1, 1, "Available", true, new RoomType(1, "king bed", 1500, 5, true)));
		hotel.setRooms(rooms);
		hotel.setHotelAddress(new HotelAddress(1, "KL", null, "VJA", 520013, "AP"));

		ApiResponse<Hotel> resHotel = new ApiResponse<Hotel>(hotel, new java.util.Date(), "");

		Payment payment = new Payment(1, "Credit Card", "Success");
		ResponseEntity<Payment> resPayment = new ResponseEntity<Payment>(payment, HttpStatus.OK);

		Mockito.when(guestFeignClient.getUserByUserName(ArgumentMatchers.anyString())).thenReturn(resUser);
		Mockito.when(hotelFeignClient.getHotelsByName(ArgumentMatchers.anyString())).thenReturn(resHotel);
		Mockito.when(paymentFeignClient.addPayment(ArgumentMatchers.any(Payment.class))).thenReturn(resPayment);
		Mockito.when(reservationRepository.save(ArgumentMatchers.any(Reservation.class))).thenReturn(reservation);

		Reservation reservationEntity = reservationService.addReservation(reservationDto).getBody().getData();
		reservationEntity.setRoomNumber(1000);
		Assertions.assertAll(() -> assertNotNull(reservationEntity),
				() -> assertEquals(reservationEntity.getBookingStatus(), reservationDto.getBookingStatus()),
				() -> assertEquals(reservationEntity.getRoomNumber(),1000));
	}

	@Test
	void getReservationsTest() {
		List<Reservation> reservations = new ArrayList<>();
		reservations.add(reservation);
		Mockito.when(reservationRepository.findAll()).thenReturn(reservations);
		List<Reservation> actualReservations = reservationService.getAllReservations();
		Assertions.assertNotNull(actualReservations);
		Assertions.assertTrue(actualReservations.size() > 0);
	}

	@Test
	void getReservationByIdTest() {
		Optional<Reservation> optionalReservation = Optional.of(reservation);
		Mockito.when(reservationRepository.findById(ArgumentMatchers.anyInt())).thenReturn(optionalReservation);
		Reservation actualReservation = reservationService.getReservationDetailsById(1);
		Assertions.assertAll(() -> assertNotNull(actualReservation),
				() -> assertEquals(actualReservation.getBookingStatus(), reservationDto.getBookingStatus()));
	}

	@Test
	void cancelReservationTest() {
		Optional<Reservation> optionalReservation = Optional.of(reservation);
		Mockito.when(reservationRepository.findById(ArgumentMatchers.anyInt())).thenReturn(optionalReservation);
		Reservation reservationEntity = reservationService.getReservationDetailsById(1);
		reservationEntity.setBookingStatus("Cancelld");
		Mockito.when(reservationRepository.save(reservationEntity)).thenReturn(reservationEntity);
		Reservation actualReservation = reservationService.cancelReservation(reservationDto, 1);
		Assertions.assertEquals(reservationEntity.getBookingStatus(), actualReservation.getBookingStatus());
	}

	@Test
	void reservationNotFoundException() {

		Optional<Reservation> optionalReservation = Optional.of(reservation);
		Mockito.when(reservationRepository.findById(1)).thenReturn(optionalReservation);
		Reservation reservation = reservationService.getReservationDetailsById(1);
		reservation.setRoomNumber(7);
		Mockito.when(reservationRepository.save(reservation)).thenReturn(reservation);

		Assertions.assertThrows(ReservationNotFoundException.class,
				() -> reservationService.getReservationDetailsById(100));
	}

}
