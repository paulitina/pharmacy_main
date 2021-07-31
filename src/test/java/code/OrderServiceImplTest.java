package code;

import code.dto.OrderProductDto;
import code.entities.Order;
import code.entities.OrderProduct;
import code.entities.OrderProductPK;
import code.entities.Product;
import code.enums.OrderStatus;
import code.repositories.OrderDao;
import code.repositories.OrderProductDao;
import code.repositories.ProductDao;
import code.services.implementation.OrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"create-order-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class OrderServiceImplTest {
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private OrderProductDao orderProductDao;

    @Autowired
    private OrderServiceImpl orderService;

    public List<OrderProductDto> createOrderProductDtoList(Long orderId, Long productId, Integer quantity, Long orderId2, Long productId2, Integer quantity2){
        OrderProductDto orderProductDto1 = new OrderProductDto(orderId, productId, quantity);
        OrderProductDto orderProductDto2 = new OrderProductDto(orderId2, productId2, quantity2);
        return Arrays.asList(orderProductDto1, orderProductDto2);
    }

    public List<OrderProduct> createOrderProductList(Long orderId, Long productId, Long orderId2, Long productId2){
        OrderProduct orderProduct1 = orderProductDao.getById(new OrderProductPK(orderId, productId));
        OrderProduct orderProduct2 = orderProductDao.getById(new OrderProductPK(orderId2, productId2));
        return Arrays.asList(orderProduct1, orderProduct2);
    }

    public List<OrderProduct> createExpectedOrderProductList4Two(Long productIdInDb, Long productId2InDb, Long orderIdInDb,
                                                             Long orderId, Long productId, Integer quantuty, Long orderId2,
                                                             Long productId2, Integer quantity2) {
        Product product1 = productDao.findById(productIdInDb).orElse(null);
        Product product2 = productDao.findById(productId2InDb).orElse(null);
        Order order = orderDao.findById(orderIdInDb).orElse(null);
        OrderProduct orderProductExp1 = new OrderProduct(orderId, productId, quantuty, product2, order);
        OrderProduct orderProductExp2 = new OrderProduct(orderId2, productId2, quantity2, product1, order);
        return Arrays.asList(orderProductExp1, orderProductExp2);
    }

    @Test
    @Transactional
    @WithMockUser(username = "userName")
    @Sql(value = {"/create-order-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void whenOrderInCartPlaceOrderThenSuccessAddedAddressAndChangedOrderStatusTest() {
        String address = "address";
        orderService.placeOrder(address);
        Order expectedOrder = new Order(1L, 1L, OrderStatus.PLACED.getStatusType(), "address", null);
        Order actualOrder = orderDao.findById(1L).get();
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    @Transactional
    @WithMockUser(username = "userName")
    @Sql(value = {"/create-order-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void whenNewOrderProductDtoListUpdateCartWithProductListThenNewListOfOrderProductsInDBTest() {
        List<OrderProductDto> orderProductDtoList = createOrderProductDtoList(1L, 2L, 2, 1L, 1L, 1);
        orderService.updateOrderProductList(orderProductDtoList);
        List<OrderProduct> actualOrderProductList = createOrderProductList(1L, 2L, 1L, 1L);
        List<OrderProduct> expectedOrderProductList = createExpectedOrderProductList4Two(1L, 2L,
                1L, 1L, 2L, 2, 1L, 1L, 1);
        assertEquals(expectedOrderProductList, actualOrderProductList);
    }

    @Test
    @Transactional
    @WithMockUser(username = "userName")
    @Sql(value = {"/create-order-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void whenDeleteProductInProductListThenProductsIsRemovedFromDBTest() {
        orderService.deleteProductInProductList(2L);
        List<OrderProduct> actualProductList = orderDao.findById(1L).get().getOrderProductList();
        Product product2 = productDao.findById(1L).get();
        Order order = orderDao.findById(1L).get();
        OrderProduct orderProduct2 = new OrderProduct(1L, 1L, 5, product2, order);
        List<OrderProduct> expectedProductList = Collections.singletonList(orderProduct2);
        assertEquals(expectedProductList, actualProductList);
    }
}