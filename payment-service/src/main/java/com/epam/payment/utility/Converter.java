package com.epam.payment.utility;

import com.epam.payment.entity.Payment;
import com.epam.payment.model.PaymentDto;

public interface Converter {
   public Payment convert(PaymentDto paymentDto);
}