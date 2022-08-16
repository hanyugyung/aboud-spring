package example.practice.clean.infrastructure.order.querydsl;

import example.practice.clean.service.order.Order;

public interface OrderCustomRepository {
    Order selectOrderByQueryDsl();
}
