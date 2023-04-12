package org.example.domain.Order;

import java.util.List;

public interface OrderService {
    int createOrder(OrderDomainDto.CreateOrderDto domainDto);
    List<OrderDomainDto.GetOrderDto> getListOrder();
}
