package org.example.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerCustomConfig {

    public final static String SERVICE_CIRCUIT_BREAKER = "ServiceCircuitBreaker";

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        return CircuitBreakerRegistry.of(
                CircuitBreakerConfig.custom()
                        .slidingWindowSize(10) // 통계 큐 크기
                        .minimumNumberOfCalls(10) // 최소 요청 수, 요청이 10번 들어왔고, 9번 실패해도 open 으로 전환하지 않음
                        .failureRateThreshold(50) // 실패 비율
                        .build()
        );
    }

    @Bean
    public CircuitBreaker circuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        return circuitBreakerRegistry.circuitBreaker(SERVICE_CIRCUIT_BREAKER);
    }

//    @Bean
//    public CircuitBreakerRegistry circuitBreakerRegistry() {
//        return CircuitBreakerRegistry.ofDefaults();
//    }
//    @Bean
//    public CircuitBreaker circuitBreaker() {
//        return circuitBreakerRegistry().circuitBreaker("SERVICE_CIRCUIT_BREAKER"
//                        , CircuitBreakerConfig.custom()
//                                .slidingWindowSize(10) // 통계 큐 크기
//                                .minimumNumberOfCalls(10) // 최소 요청 수, 요청이 10번 들어왔고, 9번 실패해도 open 으로 전환하지 않음
//                                .failureRateThreshold(50) // 실패 비율
//                                .recordExceptions(RuntimeException.class)
//                                .build()
//                );
//    }

}
