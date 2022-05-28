package com.epam.hotel.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROOM")
@Setter
@Getter
@NoArgsConstructor
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JMap
	private int id;
	
	@Column(name = "ROOM_NUMBER")
	@JMap
	private int roomNumber;
	
	@Column(name = "ROOM_STATUS")
	@JMap
	private String roomStatus;
	
	@Column(name = "IS_ACTIVE")
	@JMap
	private Boolean isActive;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ROOM_TYPE_ID")
	@JMap
	private RoomType roomType;
	
	@ManyToOne
	@JoinColumn(name = "HOTEL_ID")
	private Hotel hotel;

}
