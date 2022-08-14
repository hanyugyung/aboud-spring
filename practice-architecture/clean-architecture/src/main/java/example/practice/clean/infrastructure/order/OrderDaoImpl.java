package example.practice.clean.infrastructure.order;

import example.practice.clean.service.order.Order;
import example.practice.clean.service.order.OrderDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

    private final OrderRepository orderRepository;

    @Override
    public Order selectOrder() {
        // 여기만 교체해주면 됨
        return orderRepository.selectOrderByQueryDsl();
//         return orderRepository.selectOrderByMybatis();
//        return orderRepository.findById(1l).get();
    }

    @Override
    public void createOrder() {

    }
}
