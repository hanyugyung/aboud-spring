package example.practice.hexagonal.order.application.port.in;

public interface OrderUseCase {
    OrderDto.CreateOrderReturn createOrder(OrderDto.CreateOrderParameter orderParameter);

    // 주문 관련 사용자 요구사항
}
