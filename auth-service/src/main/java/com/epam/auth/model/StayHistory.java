package com.epam.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StayHistory {
	private int id;
	private int reservationID;
	private User user;
}
