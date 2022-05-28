package com.epam.guest.dto;

import com.googlecode.jmapper.annotations.JMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardDto {
	@JMap
	private int id;
	
	private long cardNumber;
	@JMap
	
	private String expiryDate;
	
	@JMap
	private String cardHolder;
	
	@JMap
	private String cardType;
}