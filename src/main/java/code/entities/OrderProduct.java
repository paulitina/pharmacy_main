package code.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
@IdClass(OrderProductPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderProduct {

    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable=false, updatable=false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable=false, updatable=false)
    private Order order;

    public OrderProduct(Long orderId, Long productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
