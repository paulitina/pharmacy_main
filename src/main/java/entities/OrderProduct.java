package entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_products")
@IdClass(OrderProductPK.class)
public class OrderProduct {
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product productId;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order orderId;

    @Column(name = "quantity")
    Long quantity;

    public OrderProduct() {
    }

    public OrderProduct(Product productId, Order orderId, Long quantity) {
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
