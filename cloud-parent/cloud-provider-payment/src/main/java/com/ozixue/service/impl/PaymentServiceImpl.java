package com.ozixue.service.impl;

import com.ozixue.entity.Payment;
import com.ozixue.mapper.PaymentMapper;
import com.ozixue.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment entity) {
        return paymentMapper.insert(entity);
    }

    @Override
    public Payment selectPaymentById(Long id) {
        return paymentMapper.selectById(id);
    }
}
