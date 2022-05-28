package com.epam.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROOM_TYPE")
@Setter
@Getter
@NoArgsConstructor
public class RoomType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "ROOM_TYPE")
	@JMap
	private String roomName;
	@Column(name = "RENT_PER_DAY")
	@JMap
	
	private float rentPerDay;
	@Column(name = "ROOM_CAPACITY")
	@JMap
	
	private int roomCapacity;
	@Column(name = "IS_ACTIVE")
	@JMap
	
	private Boolean isActive;
}
