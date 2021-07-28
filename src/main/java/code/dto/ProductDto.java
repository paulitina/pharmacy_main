package code.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    private Long productId;

    private String name;

    private String indications;

    private String manufacturerInfo;

    private String sideEffects;

    private Integer quantity;

    private Integer price;

    private Boolean prescribed;

    private byte[] image;

}
