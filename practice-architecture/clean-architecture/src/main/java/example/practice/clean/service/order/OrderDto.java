package example.practice.clean.service.order;

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
