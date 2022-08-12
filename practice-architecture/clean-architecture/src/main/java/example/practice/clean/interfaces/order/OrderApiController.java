package example.practice.clean.interfaces.order;

import example.practice.clean.application.OrderFacade;
import example.practice.clean.service.order.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderFacade orderFacade;

    @PostMapping("/api/orders")
    public OrderApiDto.CreateOrderResponse createOrder(@RequestBody @Valid OrderApiDto.CreateOrderRequest request) {
        OrderDto.CreateOrderParameter orderParameter = request.to();
        OrderDto.CreateOrderReturn orderReturn = orderFacade.createOrder(orderParameter);
        return OrderApiDto.CreateOrderResponse.from(orderReturn); // FIXME 성공이나 실패 상관없이 일관된 규격 필요
    }

}
