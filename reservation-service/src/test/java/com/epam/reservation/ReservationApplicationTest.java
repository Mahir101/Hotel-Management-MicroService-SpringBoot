package com.epam.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.epam.reservation.dto.ReservationDto;
import com.epam.reservation.model.Payment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

class ReservationApplicationTest {

	private ReservationDto reservationDto;

	@Autowired
	private TestRestTemplate restTemplate;

	@BeforeEach
	void beforeEach() {
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
	}

	/*
	 * @Test void addReservationTest() throws Exception {
	 * 
	 * 
	 * 
	 * 
	 * ResponseEntity<String> reservationDetails = restTemplate.exchange(
	 * "http://localhost:8084/v1/api/reservations", HttpMethod.POST, reservationDto,
	 * Reservation.class);
	 * 
	 * 
	 * 
	 * 
	 * ResponseEntity<Reservation> reservationDetails =
	 * restTemplate.postForEntity("v1/api/reservations", reservationDto,
	 * Reservation.class);
	 * 
	 * Reservation reservation = reservationDetails.getBody();
	 * Assertions.assertNotNull(reservation); Assertions.assertAll(() ->
	 * Assertions.assertEquals(1, reservation.getUserId()), () ->
	 * Assertions.assertEquals("2021-05-05", reservation.getCheckInDate()), () ->
	 * Assertions.assertEquals("2021-05-10", reservation.getCheckOutDate())); }
	 */
	
	
	
	@Test
	void testGetReservationDetails() {
	ResponseEntity<ResponseEntity> entity = restTemplate.getForEntity("v1/api/reservations", ResponseEntity.class);
	System.out.println(entity);
	//assertThat(entity.getBody().getBody().).isEqualTo("Successfully retrieved the record");
	}
	
	
	
	
	
}
