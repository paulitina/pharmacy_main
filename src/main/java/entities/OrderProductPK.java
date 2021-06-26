package entities;

import java.io.Serializable;

public class OrderProductPK implements Serializable {
    Long productId;
    Long userId;

    public OrderProductPK() {
    }

    public OrderProductPK(Long productId, Long userId) {
        this.productId = productId;
        this.userId = userId;
    }
}
