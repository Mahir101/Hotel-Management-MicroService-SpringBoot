package com.epam.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCard {
	private int id;
	private long cardNumber;
	private String expiryDate;
	private String cardHolder;
	private String cardType;
	private User user;
}
