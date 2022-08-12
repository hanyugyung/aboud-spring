package example.practice.clean.service.order;

public interface OrderService {

    // 주문을 생성한다
    OrderDto.CreateOrderReturn createOrder(OrderDto.CreateOrderParameter orderParameter);

    // 주문을 가져온다

    // 주문을 취소한다

    // ....

}
