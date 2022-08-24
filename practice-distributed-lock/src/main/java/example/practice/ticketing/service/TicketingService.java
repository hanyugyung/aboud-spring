package example.practice.ticketing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TicketingService {

    private static final String key = "TICKET-KEY";

    private Ticket ticket;

    private final RedissonClient redissonClient;

    public void issueTickets(int quantity) {
        this.ticket = new Ticket(quantity);
    }

    public int getCurrentTicketQuantity(){
        return this.ticket.getQuantity();
    }

    public void buyTicket() throws InterruptedException {

        RLock lock = redissonClient.getLock(key);

        try {
            // 최대 5초동안은 lock 을 기다려본다
            // 1초동안 lock 을 점유한다
            if(!lock.tryLock(5, 1, TimeUnit.SECONDS)) {
                // 5초가 지나도 lock 을 얻을 수 없는 경우
                throw new IllegalStateException("재구매..");
            }

            this.ticket.sell();

        } finally {
            lock.unlock();
        }
    }

}
