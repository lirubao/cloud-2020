package com.ozixue.netflix;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 自定义轮询策略
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
