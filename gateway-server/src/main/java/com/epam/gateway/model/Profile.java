package com.epam.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
