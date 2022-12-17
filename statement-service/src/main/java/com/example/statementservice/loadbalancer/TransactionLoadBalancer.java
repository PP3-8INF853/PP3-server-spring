package com.example.statementservice.loadbalancer;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.context.annotation.Bean;

@LoadBalancerClient(
"transaction-client"
)

public class TransactionLoadBalancer {
    @LoadBalanced
    @Bean
    public Feign.Builder feignBuilder(){
        return  Feign.builder();
    }
}