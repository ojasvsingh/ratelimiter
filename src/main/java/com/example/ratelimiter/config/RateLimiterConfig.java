package com.example.ratelimiter.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;

@Configuration
public class RateLimiterConfig {

    @Bean
    public Bucket createRateLimiterBucket(){
        //20tokens per minute
        Bandwidth limit = Bandwidth.classic(20,Refill.greedy(20,Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }
}