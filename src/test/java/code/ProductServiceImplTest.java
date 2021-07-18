package code;

import code.dto.ProductDto;
import code.entities.Product;
import code.repositories.ProductDao;
import code.services.implementation.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-product-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProductServiceImplTest {

    @Autowired
    private ProductDao productDao;

    private ProductServiceImpl productService;

    @Before
    public void setUp() {
        this.productService = new ProductServiceImpl(productDao);
    }

    @Test
    @Sql(value = {"/create-product-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void whenProductDtoAddProductThenSuccessProductAddedToDBTest() throws MyException {
        ProductDto productDto = new ProductDto(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        productService.addProduct(productDto);
        Product actualProduct = productDao.findById(1L).get();
        Product expectedProduct = new Product(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    @Sql(value = {"/create-product-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void whenProductDtoUpdateProductThenSuccessInfoAboutProductInDBIsUpdatedTest() throws MyException {
        ProductDto productDto = new ProductDto(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        productService.updateProduct(productDto);
        Product actualProduct = productDao.findById(1L).get();
        Product expectedProduct = new Product(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    @Sql(value = {"/create-product-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void rightProductIdGetProductInfoTest() throws MyException {
        ProductDto actualProduct = productService.getProductInfo(1L);
        ProductDto expectedProduct = new ProductDto(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    @Sql(value = {"/create-product-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void whenProductsInDBGetListOfProductsThenSuccessWeHaveListOfProductsTest() throws MyException {
        ProductDto productDto1 = new ProductDto(1L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        ProductDto productDto2 = new ProductDto(2L, "ProductName", "indications", "manufacturerInfo", "sideEffects",
                10, 1000, true, null);
        List<ProductDto> expectedProductList = new ArrayList<>();
        expectedProductList.add(productDto1);
        expectedProductList.add(productDto2);
        List<ProductDto> actualProductList = productService.getListOfProducts();
        assertEquals(expectedProductList, actualProductList);
    }

}