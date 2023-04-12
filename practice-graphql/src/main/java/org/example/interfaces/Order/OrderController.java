package org.example.interfaces.Order;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order.OrderService;
import org.example.interfaces.Order.OrderDto.GetOrderResponseDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @QueryMapping
    public List<GetOrderResponseDto> getListOrder() {
        return orderService.getListOrder()
                .stream().map(GetOrderResponseDto::of)
                .toList();
    }

    @MutationMapping
    public int createOrder(@Argument OrderDto.CreateOrderRequestDto dto) {
        return orderService.createOrder(dto.toDomainDto());
    }

}
