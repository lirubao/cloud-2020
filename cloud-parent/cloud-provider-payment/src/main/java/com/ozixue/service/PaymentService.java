package com.ozixue.service;

import com.ozixue.entity.Payment;

public interface PaymentService {

    int create(Payment payment);

    Payment selectPaymentById(Long id);
}
