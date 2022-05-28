package com.epam.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Payment {
	
	private int id;
	private String modeOfPayment;
	private String status;
	
}
