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

import java.util.ArrayList;
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
    public void whenNewOrderProductDtoListUpdateCartWithProductListThenNewListOfOrderProductsInDBTest() throws MyException {
        OrderProductDto orderProductDto1 = new OrderProductDto(1L, 2L, 2);
        OrderProductDto orderProductDto2 = new OrderProductDto(1L, 1L, 1);
        List<OrderProductDto> orderProductDtoList = new ArrayList<>();
        orderProductDtoList.add(orderProductDto1);
        orderProductDtoList.add(orderProductDto2);
        orderService.updateOrderProductList(orderProductDtoList);
        List<OrderProduct> actualOrderProductList = new ArrayList<>();
        OrderProduct orderProduct1 = orderProductDao.getById(new OrderProductPK(1L, 2L));
        OrderProduct orderProduct2 = orderProductDao.getById(new OrderProductPK(1L, 1L));
        actualOrderProductList.add(orderProduct1);
        actualOrderProductList.add(orderProduct2);
        List<OrderProduct> expectedOrderProductList = new ArrayList<>();
        Product product1 = productDao.findById(1L).get();
        Product product2 = productDao.findById(2L).get();
        Order order = orderDao.findById(1L).get();
        OrderProduct orderProductExp1 = new OrderProduct(1L, 2L, 2, product2, order);
        OrderProduct orderProductExp2 = new OrderProduct(1L, 1L, 1, product1, order);
        expectedOrderProductList.add(orderProductExp1);
        expectedOrderProductList.add(orderProductExp2);
        assertEquals(expectedOrderProductList, actualOrderProductList);
    }

    @Test
    @Transactional
    @WithMockUser(username = "userName")
    @Sql(value = {"/create-order-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void whenDeleteProductInProductListThenProductsIsRemovedFromDBTest(){
        orderService.deleteProductInProductList(2L);
        List<OrderProduct> actualProductList = orderDao.findById(1L).get().getOrderProductList();
        Product product2 = productDao.findById(1L).get();
        Order order = orderDao.findById(1L).get();
        OrderProduct orderProduct2 = new OrderProduct(1L, 1L, 5, product2, order);
        List<OrderProduct> expectedProductList = Collections.singletonList(orderProduct2);
        assertEquals(expectedProductList, actualProductList);
    }
}