package code.services.implementation;

import code.MyException;
import code.dto.ProductDto;
import code.entities.Product;
import code.repositories.ProductDao;
import code.services.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    //return product from db online
    public ProductDto createProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setName(product.getName());
        productDto.setIndications(product.getIndications());
        productDto.setManufacturerInfo(product.getManufacturerInfo());
        productDto.setSideEffects(product.getSideEffects());
        productDto.setQuantity(product.getQuantity());
        productDto.setPrescribed(product.getPrescribed());
        productDto.setPrice(product.getPrice());
        productDto.setImage(productDto.getImage());
        return productDto;
    }

    //add created dto product to DB
    @Override
    public Product addProduct(ProductDto productDto) throws MyException {
        return productDao.save(new Product(null, productDto.getName(), productDto.getIndications(), productDto.getManufacturerInfo(),
                productDto.getSideEffects(), productDto.getQuantity(), productDto.getPrice(), productDto.getPrescribed(),
                productDto.getImage()));
    }

    @Override
    public Product updateProduct(ProductDto productDto) throws MyException {
        Product product = productDao.findById(productDto.getProductId()).get();
        product.setName(productDto.getName());
        product.setIndications(productDto.getIndications());
        product.setSideEffects(productDto.getSideEffects());
        product.setManufacturerInfo(productDto.getManufacturerInfo());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(product.getPrice());
        product.setPrescribed(productDto.getPrescribed());
        product.setImage(productDto.getImage());
        productDao.save(product);
        return product;
    }

    @Override
    public List<ProductDto> getListOfProducts() {
        return productDao.findAllProducts().stream()
                .map(this::createProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductInfo(Long productId) throws MyException {
        Product product = productDao.findById(productId).get();
        ProductDto productDto;
        return productDto = createProductDto(product);
    }
}
