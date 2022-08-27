package example.practice.ticketing.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TicketingServiceTest {

    @Autowired
    private TicketingService ticketingService;

    private class Buyer implements Runnable {

        private final CountDownLatch countDownLatch;

        public Buyer(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            ticketingService.buyTicket();
            countDownLatch.countDown();
        }
    }

    @Test
    @DisplayName("50명의 구매자가 50장의 티켓을 동시다발적으로 구매한다")
    void buyTicket() throws InterruptedException {


        int ticketCount = 50;
        ticketingService.issueTickets(ticketCount);
        assertEquals(ticketCount, ticketingService.getCurrentTicketQuantity());

        CountDownLatch countDownLatch = new CountDownLatch(ticketCount);
        List<Thread> buyer = Stream.generate(() -> new Thread(new Buyer(countDownLatch)))
                .limit(ticketCount).toList();

        buyer.forEach(Thread::start);
        countDownLatch.await(); // 스레드를 기다려준다

        assertEquals(0, ticketingService.getCurrentTicketQuantity());
    }

}