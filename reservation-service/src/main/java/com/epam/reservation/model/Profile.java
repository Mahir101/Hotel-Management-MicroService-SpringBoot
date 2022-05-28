package com.epam.reservation.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class Profile {
	
	private int id;
	private String firstName;
	private String lastName;
	private String emailid;
	private long mobileNumber;
	private String street;
	private String city;
	private String state;
	private int pinCode;
	private String country;
	
}
