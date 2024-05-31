package com.cozz.provider.service.impl;

import com.cozz.common.entity.Payment;
import com.cozz.provider.mapper.PaymentMapper;
import com.cozz.provider.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zt
 * @date 2024/2/22
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    public int create(Payment payment) {
        return paymentMapper.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
