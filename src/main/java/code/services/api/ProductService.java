package code.services.api;

import code.MyException;
import code.dto.ProductDto;
import code.entities.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(ProductDto productDto) throws MyException;

    Product updateProduct(ProductDto productDto) throws MyException;

    List<ProductDto> getListOfProducts();

    ProductDto getProductInfo(Long productId) throws MyException;
}
