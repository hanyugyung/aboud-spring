package example.practice.hexagonal.order.application.port.out;

// FIXME 이벤트 발행자.. 네이밍을 어떻게 할지
public interface OrderEvent {

    void sendNotification();

}
