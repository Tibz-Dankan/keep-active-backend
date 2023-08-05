package com.example.keepactivebackend.request;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@EnableScheduling
@Configuration
public class RequestConfig {
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
