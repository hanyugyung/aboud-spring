package example.practice.hexagonal.order.application;

import example.practice.hexagonal.order.application.port.in.OrderDto;
import example.practice.hexagonal.order.application.port.in.OrderUseCase;
import example.practice.hexagonal.order.application.port.out.OrderEvent;
import example.practice.hexagonal.order.application.port.out.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {

    private final OrderRepository orderRepository;

    private final OrderEvent orderEvent;

    @Override
    public OrderDto.CreateOrderReturn createOrder(OrderDto.CreateOrderParameter orderParameter) {

        // TODO 영속성 처리
        // orderRepository

        // TODO 주문 완료 알림 처리
        // orderEvent

        return null;
    }
}
