package com.cozz.provider.service;

import com.cozz.common.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author zt
 * @date 2024/2/22
 **/
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
