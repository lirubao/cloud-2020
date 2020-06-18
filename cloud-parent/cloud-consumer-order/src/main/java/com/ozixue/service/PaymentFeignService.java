package com.ozixue.service;

import com.ozixue.entity.Payment;
import com.ozixue.vo.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-payment-provider-service", fallback = PaymentFeignServiceFB.class)
public interface PaymentFeignService {

    @GetMapping("/payment/find/{id}")
    JsonResult<Payment> findPaymentById(@PathVariable("id") Long id);
}
