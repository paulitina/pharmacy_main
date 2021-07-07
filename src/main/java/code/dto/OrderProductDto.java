package code.dto;

import code.entities.Order;
import code.entities.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;



@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class OrderProductDto {
    private Product productId;

    private Order orderId;

    private int quantity;
}
