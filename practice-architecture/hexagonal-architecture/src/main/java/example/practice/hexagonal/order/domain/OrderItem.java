package example.practice.hexagonal.order.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "item_id")
    private Long itemId;

    @NotNull
    @Column(name = "item_name")
    private String itemName;

    @NotNull
    @Column(name = "item_price")
    private Long itemPrice;

    @NotNull
    private Integer count;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
