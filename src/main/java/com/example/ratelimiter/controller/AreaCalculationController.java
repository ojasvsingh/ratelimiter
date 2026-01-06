package com.example.ratelimiter.controller;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap; //allows multiple threads to read and write data simultaneously without locking the entire map

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

import jakarta.servlet.http.HttpServletRequest; //from the java serverlet API to get HTTP header
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ratelimiter.model.AreaV1;
import com.example.ratelimiter.model.RectangleDimensionsV1;


@RestController
public class AreaCalculationController{

    //Thread Safe map: IP address -> token bucket
    private final Map<String, Bucket> ipBuckets = new ConcurrentHashMap<>();

    //creates a new bucket that greedly fills 10 tokens all at once every minute
    private Bucket createNewBucket() {
        //10 requests per minute
        Bandwidth limit = Bandwidth.classic(10,Refill.greedy(10,Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build(); 
    }

    //endpoint path: http://localhost:8080/api/v1/area/rectangle


    //HttpServletRequest: gives access to request metadata, including IP address
    @PostMapping("/api/v1/area/rectangle")
    public ResponseEntity<?> rectangle(@RequestBody RectangleDimensionsV1 dimensions, HttpServletRequest request){
       
       String userIp = request.getRemoteAddr();

        //Retrieve the bucket for the userIp address. If not present then create a new bucket
       Bucket bucket = ipBuckets.computeIfAbsent(userIp, ip ->createNewBucket());

        //try to consume a token
        if(bucket.tryConsume(1)){
            AreaV1 result =  new AreaV1("rectangle", dimensions.getLength() * dimensions.getWidth());
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests");
    }
}