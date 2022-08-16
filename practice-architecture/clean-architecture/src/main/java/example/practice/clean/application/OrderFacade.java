package example.practice.clean.application;

import example.practice.clean.service.notification.NotificationService;
import example.practice.clean.service.order.OrderDto;
import example.practice.clean.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;

    private final NotificationService notificationService;

    public OrderDto.CreateOrderReturn createOrder(OrderDto.CreateOrderParameter orderParameter){

        // 주문 관련 데이터 생성
        OrderDto.CreateOrderReturn orderReturn = orderService.createOrder(orderParameter);

        // 주문 완료 알림
        notificationService.sendNotification();

        return orderReturn;
    }

}
