package com.ozixue.controller;

import com.ozixue.entity.Payment;
import com.ozixue.service.PaymentFeignService;
import com.ozixue.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/consumer")
@RefreshScope
public class OrderController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;


    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return "serverPort:" + serverPort + "\t\n\n configInfo: " + configInfo;
    }


    @GetMapping("/payment/find/{id}")
    public JsonResult<Payment> findPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.findPaymentById(id);
    }
}
