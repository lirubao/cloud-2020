package com.ozixue.service;

import com.ozixue.entity.Payment;
import com.ozixue.vo.JsonResult;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignServiceFB implements PaymentFeignService {

    @Override
    public JsonResult<Payment> findPaymentById(Long id) {
        // TODO: 熔断/降级后要执行的业务
        return new JsonResult<>(200, "熔断/降级");
    }
}
