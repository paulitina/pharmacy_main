package code.entities;

import code.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "my_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @Column(name = "order_id")
    private Long orderId;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private String status;

    @Column(name = "address")
    private String address;

    @OneToMany
    List<OrderProduct> orderProductList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    OrderStatus orderStatus;

    public Order(Long orderId, Long userId, String status, String address, List<OrderProduct> orderProductList) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = orderStatus.getStatusType();
        this.address = address;
        this.orderProductList = orderProductList;
    }
}
