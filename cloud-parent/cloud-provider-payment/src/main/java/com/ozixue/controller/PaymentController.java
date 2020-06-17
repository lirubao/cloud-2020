package com.ozixue.controller;

import com.ozixue.entity.Payment;
import com.ozixue.service.PaymentService;
import com.ozixue.vo.JsonResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/payment")
@Api(value = "provider 提供者", tags = "Payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    // 通过服务发现来获得该服务的信息
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/create")
    public JsonResult<Payment> create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.debug("***********插入成功: {}", result);
        if (result > 0)
            return new JsonResult<>(200, "插入成功,port: " + port);
        log.debug("***********插入失败: {}", result);
        return null;
    }


    @GetMapping("/find/{id}")
    public JsonResult<Payment> findPaymentById(@PathVariable Long id) {
        // 模拟超时
        timeout();
        Payment payment = paymentService.selectPaymentById(id);
        if (StringUtils.isEmpty(payment))
            return new JsonResult<>(200, "没有这个信息,port: " + port, null);
        return new JsonResult<>(200, "查询成功,port: " + port, payment);
    }

    @GetMapping("/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        services.forEach(element -> {
            log.debug("*********** element: {}", element);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-provider-service");
        instances.forEach(instance -> {
            log.debug(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        });
        return this.discoveryClient;
    }

    // 模拟超时
    private void timeout() {
        long t = new Random().nextInt(5000);
        if (Math.random() < 0.6) {
            log.debug("cloud-payment-provider-service-{}- 暂停:{}", port, t);
            try {
                Thread.sleep(t);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
