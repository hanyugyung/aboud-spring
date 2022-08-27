package example.practice.ticketing.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@AllArgsConstructor
public class Ticket {
    /**
     * 티켓 수량
     */
    private int quantity;

    public void sell() {
        this.quantity -= 1;
        System.out.println("현재 남은 티켓 개수 : " + this.quantity);
    }
}
