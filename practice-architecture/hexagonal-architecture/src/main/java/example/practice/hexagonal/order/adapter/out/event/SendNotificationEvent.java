package example.practice.hexagonal.order.adapter.out.event;

import example.practice.hexagonal.order.application.port.out.OrderEvent;
import org.springframework.context.ApplicationEvent;

public class SendNotificationEvent extends ApplicationEvent implements OrderEvent {

    public SendNotificationEvent(Object source) {
        super(source);
    }

    @Override
    public void sendNotification() { // FIXME 주문완료알림에 대해 구체적인 함수이름을 가져가야 할까 고민

    }
}
