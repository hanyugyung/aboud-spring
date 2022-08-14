package example.practice.clean.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao; // TODO 이름이 적절한지 좀 더 고민해보기

    @Override
    @Transactional
    public OrderDto.CreateOrderReturn createOrder(OrderDto.CreateOrderParameter orderParameter) {
        Order order = orderParameter.toEntity();

        // data access 를 인터페이스화 하여 db 가 변경되어야 해도 비즈니스 로직은 변경할 필요 없음
        // query dsl, mybatis, jpa, nosql 의 세부사항 구현의 변경이 고수준 추상화 로직에 영향 주지 않음.
        orderDao.selectOrder();
        // orderDao.createOrder();
        return new OrderDto.CreateOrderReturn(order);
    }
}
