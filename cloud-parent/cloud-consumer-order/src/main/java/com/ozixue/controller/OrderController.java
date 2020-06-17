package com.ozixue.controller;

import com.ozixue.entity.Payment;
import com.ozixue.netflix.LoadBalancer;
import com.ozixue.vo.JsonResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@Api(value = "order 消费者", tags = "restTemplate")
@RequestMapping("/consumer")
public class OrderController {

    public static final String PAYMENT_URL = "http://cloud-payment-provider-service";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @GetMapping("/payment/create")
    public JsonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, JsonResult.class);
    }

    @GetMapping("/payment/find/{id}")
    public JsonResult<Payment> findPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/find/" + id, JsonResult.class);
    }

    @GetMapping("/payment/lb")
    public String getPaymentLoadBalacer() {
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-provider-service");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);

        return restTemplate.getForObject(serviceInstance.getUri() + "/payment/discovery", String.class);
    }
}
