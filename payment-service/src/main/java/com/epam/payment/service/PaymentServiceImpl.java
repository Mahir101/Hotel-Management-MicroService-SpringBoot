package com.epam.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.payment.entity.Payment;
import com.epam.payment.model.PaymentDto;
import com.epam.payment.repository.PaymentRepository;
import com.epam.payment.utility.PaymentUtility;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentRepository paymentRepository;

	public Payment addPayment(PaymentDto paymentDto) {
		return paymentRepository.save(new PaymentUtility().convert(paymentDto));
	}
}
