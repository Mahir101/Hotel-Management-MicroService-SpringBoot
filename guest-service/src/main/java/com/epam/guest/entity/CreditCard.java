package com.epam.guest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.jmapper.annotations.JMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CREDIT_CARD")
@Getter
@Setter
@NoArgsConstructor
public class CreditCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Column(name = "CARD_NUMBER")
	@JMap
	private long cardNumber;
	
	@NotNull
	@Column(name = "EXPAIR_DATE")
	@JMap
	private String expiryDate;
	
	@NotNull
	@Column(name = "CARD_HOLDER_NAME")
	@JMap
	private String cardHolder;
	
	@NotNull
	@Column(name = "CARD_TYPE")
	@JMap
	private String cardType;
	
	@ManyToOne()
	@JoinColumn(name = "USER_ID")
	@JsonIgnore
	private User user;
}
