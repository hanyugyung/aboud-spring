package example.practice.ticketing.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Ticket {
    /**
     * 티켓 수량
     */
    private int quantity;

    public void sell() {
        this.quantity -= 1;
    }
}
