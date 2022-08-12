package example.practice.clean.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // private final OrderRepository orderRepository; TODO 패키지 구조를 어떻게 가져갈까..

    @Override
    @Transactional
    public OrderDto.CreateOrderReturn createOrder(OrderDto.CreateOrderParameter orderParameter) {
        Order order = orderParameter.toEntity();
        // orderRepository.save(order);
        return new OrderDto.CreateOrderReturn(order);
    }
}
