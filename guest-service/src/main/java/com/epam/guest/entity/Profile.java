package com.epam.guest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROFILE_DETAILS")
@Setter
@Getter
@NoArgsConstructor

public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "FIRST_NAME")
	@JMap
	private String firstName;
	
	@NotNull
	@Column(name = "LAST_NAME")
	@JMap
	private String lastName;
	
	@NotNull
	@Email(regexp = "^(.+)@(.+)$")
	@Column(name = "EMAIL")
	@JMap
	private String emailid;
	
	@NotNull
	@Min(10)
	@Column(name = "MOBILE_NO")
	@JMap
	private long mobileNumber;
	
	@Column(name = "STREET")
	@JMap
	private String street;
	
	@Column(name = "CITY")
	@JMap
	private String city;
	
	@Column(name = "STATE")
	@JMap
	private String state;
	
	@NotNull
	@Column(name = "PINCODE")
	@JMap
	private int pinCode;
	
	@Column(name = "COUNTRY")
	@JMap
	private String country;
}
