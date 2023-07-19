package org.example.service;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.config.CircuitBreakerCustomConfig;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @CircuitBreaker(name = CircuitBreakerCustomConfig.SERVICE_CIRCUIT_BREAKER, fallbackMethod = "fallbackMethod")
    public Code method1(int input) {
        System.out.println("Start method1, input :: " + input);
        throw new RuntimeException("fail");
    }

    public Code fallbackMethod(int input, CallNotPermittedException e) {
        System.out.println("fallback, Not Called, C/B OPENED, Code :: FAIL, input :: " + input + ", e.message :: " + e.getMessage());
        return Code.FAIL;
    }


    public Code fallbackMethod(int input, Exception e) {
        System.out.println("fallback, Code :: FAIL, input :: " + input + ", e.message :: " + e.getMessage());
        return Code.FAIL;
    }

    public enum Code {
        SUCCESS, FAIL
    }

}
