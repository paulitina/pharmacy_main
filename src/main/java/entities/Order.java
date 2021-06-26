package entities;


import javax.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(name="my_seq",sequenceName="my_sequence", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq")
    @Column(name = "order_id")
    Long orderId;


    @ManyToOne
    @JoinColumn(name = "user_id")
    User userId;

    @Column(name = "status")
    String status;

    @Column(name = "address")
    String address;

    public Order() {
    }

    public Order(Long orderId, User userId, String status, String address) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
        this.address = address;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
