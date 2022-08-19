package example.practice.hexagonal.order.application.port.in;

import example.practice.hexagonal.order.domain.Order;

public class OrderDto {

    public static class CreateOrderParameter {

        public Order toEntity() {
            return new Order();
        }
    }

    public static class CreateOrderReturn {

        public CreateOrderReturn(Order order) {
        }
    }

}
