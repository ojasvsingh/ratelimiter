package com.example.ratelimiter.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimiterConfig {

    // @Bean
    // public Bucket createRateLimiterBucket(){
    //     //10tokens per minute
    //     Bandwidth limit = Bandwidth.classic(10,Refill.greedy(10,Duration.ofMinutes(1)));
    //     return Bucket.builder().addLimit(limit).build();
    // }
}