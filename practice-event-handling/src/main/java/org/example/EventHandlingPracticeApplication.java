package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class EventHandlingPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventHandlingPracticeApplication.class, args);
    }
}