package com.ozixue.controller;

import com.ozixue.entity.Payment;
import com.ozixue.service.PaymentService;
import com.ozixue.vo.JsonResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/payment")
@Api(value = "provider 提供者", tags = "Payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/create")
    public JsonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***********插入成功: {}", result);
        if (result > 0)
            return new JsonResult<>(200, "插入成功,port: " + port);
        log.info("***********插入失败: {}", result);
        return null;
    }


    @GetMapping("/find/{id}")
    public JsonResult<Payment> findPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.selectPaymentById(id);
        if (StringUtils.isEmpty(payment))
            return new JsonResult<>(200, "没有这个信息,port: " + port, null);
        return new JsonResult<>(200, "查询成功,port: " + port, payment);
    }
}
