package com.epam.payment.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

import com.epam.payment.model.PaymentDto;
import com.epam.payment.service.PaymentService;
import com.epam.payment.utility.PaymentUtility;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PaymentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private PaymentService paymentService;

	private PaymentUtility paymentUtility;

	private PaymentDto paymentDto;

	@BeforeEach
	void beforeEach() {
		paymentUtility = new PaymentUtility();
		paymentDto = new PaymentDto();
		paymentDto.setId(111111);
		paymentDto.setModeOfPayment("Credit Card");
		paymentDto.setStatus("Success");
	}

	@Test
	void addPaymentTest() throws Exception {
		String paymentData = objectMapper.writeValueAsString(paymentDto);
		Mockito.when(paymentService.addPayment(paymentDto)).thenReturn(paymentUtility.convert(paymentDto));
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/api/payments").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(paymentData)).andExpect(MockMvcResultMatchers.status().isCreated());
	}

}
