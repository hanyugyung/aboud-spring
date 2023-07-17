package org.example.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.config.CircuitBreakerCustomConfig;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    @CircuitBreaker(name = CircuitBreakerCustomConfig.SERVICE_CIRCUIT_BREAKER, fallbackMethod = "fallbackMethod")
    public Code method1(int input) {

        if(input > 4) {
            throw new RuntimeException("'input' bigger than 4");
        }

        System.out.println("Code :: SUCCESS, input :: " + input);
        return Code.SUCCESS;
    }

    public Code fallbackMethod(int input, RuntimeException e) {
        System.out.println("Code :: FAIL, input :: " + input);
        return Code.FAIL;
    }

    public enum Code {
        SUCCESS, FAIL
    }

}
