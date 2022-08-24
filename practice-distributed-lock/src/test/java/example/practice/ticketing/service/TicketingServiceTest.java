package example.practice.ticketing.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TicketingServiceTest {

    @Autowired
    private TicketingService ticketingService;

    @Test
    void buyTicket() {

        int ticketCount = 50;
        ticketingService.issueTickets(ticketCount);
        assertEquals(ticketCount, ticketingService.getCurrentTicketQuantity());



        assertEquals(0, ticketingService.getCurrentTicketQuantity());
    }

}