package org.example.interfaces.Order;

import lombok.*;
import org.example.domain.Order.OrderDomainDto;

public class OrderDto {

    @Setter // setter 가 없으면 controller 에서 argument 의 속성 값이 전부 null 로 들어옴.
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateOrderRequestDto {
        private Integer productId;
        private Integer count;

        public OrderDomainDto.CreateOrderDto toDomainDto() {
            return OrderDomainDto.CreateOrderDto.builder()
                    .productId(Long.parseLong(this.productId.toString()))
                    .count(this.count)
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetOrderResponseDto {
        private Long id;
        private Long productId;
        private Integer count;

        public static OrderDto.GetOrderResponseDto of(OrderDomainDto.GetOrderDto domainDto) {
            return OrderDto.GetOrderResponseDto.builder()
                    .id(domainDto.getId())
                    .productId(domainDto.getProductId())
                    .count(domainDto.getCount())
                    .build();
        }
    }

}
