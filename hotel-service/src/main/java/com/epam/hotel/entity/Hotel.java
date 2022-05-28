package com.epam.hotel.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HOTEL")
@Setter
@Getter
@NoArgsConstructor
public class Hotel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JMap
	private int id;
	
	@Column(name = "HOTEL_NAME")
	@JMap
	private String hotelName;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "HOTEL_ADDRESS_ID")
	@JMap
	private HotelAddress hotelAddress;
	
	@Column(name = "IS_ACTIVE")
	@JMap
	private Boolean isActive;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "hotel")
	@JMap
	private List<Room> rooms;
}
