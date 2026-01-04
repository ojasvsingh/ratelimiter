package com.example.ratelimiter.controller;

import java.time.Duration;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AreaCalculationController{

    private final Bucket rateLimiterBucket;

    public AreaCalculationController(){
        //10 requests per minute
        Bandwidth limit = Bandwidth.classic(10,Refill.greedy(10,Duration.ofMinutes(1)));
        this.rateLimiterBucket = Bucket.builder().addLimit(limit).build();
    }


    //endpoint path: http://localhost:8080/api/v1/area/rectangle

    @PostMapping("/api/v1/area/rectangle")
    public ResponseEntity<AreaV1> rectangle(@RequestBody RectangleDimensionsV1 dimensions){
        //try to consume a token
        if(rateLimiterBucket.tryConsume(1)){
            AreaV1 result =  new AreaV1("rectangle", dimensions.getLength() * dimensions.getWidth());
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
    }
}