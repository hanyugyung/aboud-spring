package org.example.domain.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class OrderDomainDto {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateOrderDto {
        private Long productId;
        private Integer count;

        public Order toOrder() {
            return Order.builder()
                    .id(Order.getOrderId())
                    .productId(this.productId)
                    .count(this.count)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetOrderDto {
        private Long id;
        private Long productId;
        private Integer count;

        public static GetOrderDto of(Order order) {
            return GetOrderDto.builder()
                    .id(order.getId())
                    .productId(order.getProductId())
                    .count(order.getCount())
                    .build();
        }
    }
}
