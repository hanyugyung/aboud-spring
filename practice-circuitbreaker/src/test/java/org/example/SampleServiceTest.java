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

        // 20번 호출 중 실패비율 설정값에 의해 input 11 부터는 회로가 열린다
        for(int i = 1; i <= 20; i++) {
            sampleService.method1(i);
        }

    }


}
