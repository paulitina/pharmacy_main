package code.entities;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductPK implements Serializable {
    Long orderId;

    Long productId;
}
