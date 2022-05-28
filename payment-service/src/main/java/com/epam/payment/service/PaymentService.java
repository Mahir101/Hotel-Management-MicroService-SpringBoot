package com.epam.payment.service;

import com.epam.payment.entity.Payment;
import com.epam.payment.model.PaymentDto;

public interface PaymentService {

	public Payment addPayment(PaymentDto paymentDto);

}
