package com.epam.hotel.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
	
	private T data;
	
	private LocalDate date;
	
	private String message;

}