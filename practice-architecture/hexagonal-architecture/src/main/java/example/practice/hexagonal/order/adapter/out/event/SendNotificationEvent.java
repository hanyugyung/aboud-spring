package example.practice.hexagonal.order.adapter.out.event;

import org.springframework.context.ApplicationEvent;

public class SendNotificationEvent extends ApplicationEvent {

    public SendNotificationEvent(Object source) {
        super(source);
    }

}
