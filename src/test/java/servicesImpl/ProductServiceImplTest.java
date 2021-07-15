package servicesImpl;

import code.MyException;
import code.dto.ProductDto;
import code.entities.Product;
import code.repositories.ProductDao;
import code.services.implementation.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {
    @Mock
    ProductDao productDao;

    private ProductServiceImpl productService;

    @Before
    public void setUp() {
        this.productService = new ProductServiceImpl(productDao);
    }

    @Test
    public void addProductTest() throws MyException {
        ProductDto productDto = new ProductDto(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        Product currentProduct = productService.addProduct(productDto);
        Product anotherProduct = new Product(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        if (currentProduct.equals(anotherProduct)) {
            System.out.println(":) Everything is fine from productServiceImplTest/addProduct");
        } else {
            System.out.println(":( something went wrong from productServiceImplTest/addProduct");
        }
    }

    @Test
    public void updateProductTest() throws MyException {
        ProductDto productDto = new ProductDto(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        given(productDao.findById(1L)).willReturn(null);
        Product currentProduct = productService.updateProduct(productDto);
        Product anotherProduct = new Product(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        if (currentProduct.equals(anotherProduct)) {
            System.out.println(":) Everything is fine from productServiceImplTest/updateProduct");
        } else {
            System.out.println(":( something went wrong from productServiceImplTest/updateProduct");
        }
    }

    @Test
    public void wrongProductIdGetProductInfoTest() throws MyException {
        productService.getProductInfo(1L);
        given(productDao.findById(1L)).willReturn(null);
    }

    @Test
    public void rightProductIdGetProductInfoTest() throws MyException {
        Product product = new Product(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        ProductDto currentProduct = productService.getProductInfo(1L);
        Mockito.doReturn(product).when(productDao.findById(1L));
//        given(productDao.findById(1L)).willReturn(product);

        ProductDto anotherProduct = new ProductDto(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        if (currentProduct.equals(anotherProduct)) {
            System.out.println(":) Everything is fine from productServiceImplTest/getProductInfo");
        } else {
            System.out.println(":( something went wrong from productServiceImplTest/getProductInfo");
        }
    }

    @Test
    public void getListOfProductsTest() throws MyException {
        ProductDto productDto1 = new ProductDto(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        ProductDto productDto2 = new ProductDto(2L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto1);
        productDtoList.add(productDto2);
        List<ProductDto> currentProductList = productService.getListOfProducts();
        List<ProductDto> anotherProductList = productDtoList;
        if (currentProductList.equals(anotherProductList)) {
            System.out.println(":) Everything is fine from productServiceImplTest/getListOfProducts");
        } else {
            System.out.println(":( something went wrong from productServiceImplTest/getListOfProducts");
        }
    }

}