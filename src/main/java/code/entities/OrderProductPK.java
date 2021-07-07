package code.entities;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductPK implements Serializable {
    Long productId;

    Long userId;
}
