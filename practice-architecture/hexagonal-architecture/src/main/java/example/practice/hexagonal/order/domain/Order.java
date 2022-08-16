package example.practice.hexagonal.order.domain;

import example.practice.clean.service.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Getter
    @AllArgsConstructor
    public enum OrderStatus {
        WAIT("입금대기중")
        , PAY_COMPLETE("결제완료")
        , DELIVERY_PREPARE("배송준비중")
        , IN_DELIVERY("배송중")
        , DELIVERY_COMPLETE("배송완료");

        private final String description;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItem> orderItemList = List.of();

    public void payComplete() { // 엔티티 내부 규칙
        this.orderStatus = OrderStatus.PAY_COMPLETE;
    }


}
