package code.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto {
    private Long orderId;

    private String status;

    private String address;

    private List<OrderProductDto> productDtoList;
}
