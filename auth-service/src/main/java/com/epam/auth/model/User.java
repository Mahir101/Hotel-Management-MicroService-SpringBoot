package com.epam.auth.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private Boolean status;
	private Profile profile;
	private List<CreditCard> creditCards = new ArrayList<>();
	private List<StayHistory> stayHistory = new ArrayList<>();
	private String userName;
	private String password;
}
