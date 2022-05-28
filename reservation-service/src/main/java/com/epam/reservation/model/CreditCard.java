package com.epam.reservation.model;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CreditCard {
	
	private int id;
	private long cardNumber;
	private String expiryDate;
	private String cardHolder;
	private String cardType;
	
}
