package com.example.statementservice.loadbalancer;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

/*
@LoadBalancerClient("account-service")
public class AccountLoadBalancer {
    @LoadBalanced
    @Bean
    public Feign.Builder feignBuilder(){
        return  Feign.builder();
    }
}
*/
