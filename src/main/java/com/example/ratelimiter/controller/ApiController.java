package com.example.ratelimiter.controller;

import io.github.bucket4j.Bucket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
public class ApiController{

    @Autowired
    private Bucket rateLimiterBucket;

    @GetMapping("/api/data")
    public ResponseEntity<String> getData() {
        //try to consume a token
        if(rateLimiterBucket.tryConsume(1)){
            return ResponseEntity.ok("Request accepted");
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
    }
}