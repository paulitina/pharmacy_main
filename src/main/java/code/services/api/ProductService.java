package code.services.api;

import code.dto.ProductDto;
import code.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductDto productDto);

    Product updateProduct(ProductDto productDto);

    List<ProductDto> getListOfProducts();

    ProductDto getProductInfo(Long productId);
}
