package code.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@EqualsAndHashCode(exclude = {"user", "orderProductList"})
@ToString(exclude = {"user", "orderProductList"})
@NoArgsConstructor
public class Order {
    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    private String status;

    @Column(name = "address")
    private String address;

    @OneToMany
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private List<OrderProduct> orderProductList;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;


    public Order(Long orderId, Long userId, String status, String address, List<OrderProduct> orderProductList) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
        this.address = address;
        this.orderProductList = orderProductList;
    }
}
