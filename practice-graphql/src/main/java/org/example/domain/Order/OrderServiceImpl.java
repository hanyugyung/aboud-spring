package org.example.domain.Order;

import org.example.PersistenceOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public int createOrder(OrderDomainDto.CreateOrderDto domainDto) {
        // persistence
        return PersistenceOrder.addOrder(domainDto.toOrder());
    }

    @Override
    public List<OrderDomainDto.GetOrderDto> getListOrder() {
        return PersistenceOrder.getList()
                .stream().map(OrderDomainDto.GetOrderDto::of)
                .toList();
    }
}
