package org.example;

import org.example.service.SampleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleServiceTest {

    @Autowired
    private SampleService sampleService;

    @Test
    public void circuit_breaker_테스트() {

        for(int i = 0; i < 20; i++) {
            try {
                sampleService.method1(i);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }

        }
    }


}
