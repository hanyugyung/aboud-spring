package example.practice.clean.infrastructure.order;

import example.practice.clean.infrastructure.order.mybatis.OrderJdbcRepository;
import example.practice.clean.infrastructure.order.querydsl.OrderCustomRepository;
import example.practice.clean.infrastructure.order.redis.OrderRedisRepository;
import example.practice.clean.service.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderJdbcRepository
        , OrderCustomRepository, OrderRedisRepository { // db 가 변경되어야 하면 여기 추가하면 됨
}
