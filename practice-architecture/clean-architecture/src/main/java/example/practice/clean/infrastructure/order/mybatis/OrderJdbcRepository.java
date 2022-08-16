package example.practice.clean.infrastructure.order.mybatis;

import example.practice.clean.service.order.Order;

public interface OrderJdbcRepository {
    Order selectOrderByMybatis();
}
