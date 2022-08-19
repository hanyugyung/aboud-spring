package example.practice.hexagonal.order.adapter.in.interfaces;

import example.practice.hexagonal.order.application.port.in.OrderDto;
import example.practice.hexagonal.order.application.port.in.OrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderUseCase orderUseCase;

    @PostMapping("/api/orders")
    public OrderApiDto.CreateOrderResponse createOrder(@RequestBody @Valid OrderApiDto.CreateOrderRequest request) {
        OrderDto.CreateOrderParameter orderParameter = request.to();
        OrderDto.CreateOrderReturn orderReturn = orderUseCase.createOrder(orderParameter);
        return OrderApiDto.CreateOrderResponse.from(orderReturn); // FIXME 성공이나 실패 상관없이 일관된 규격 필요
    }

}

