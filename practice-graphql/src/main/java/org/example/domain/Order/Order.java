package org.example.domain.Order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static Long orderId = 1L;

    private Long id;

    private Long productId;

    private Integer count;

    public static Long getOrderId() {
        Long tmp = orderId;
        orderId++;
        return tmp;
    }
}
