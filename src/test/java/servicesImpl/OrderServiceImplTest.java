package servicesImpl;

import code.MyException;
import code.dto.OrderProductDto;
import code.entities.Order;
import code.entities.OrderProduct;
import code.enums.OrderStatus;
import code.repositories.OrderDao;
import code.repositories.OrderProductDao;
import code.services.implementation.OrderServiceImpl;
import code.services.implementation.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Mock
    OrderDao orderDao;

    @Mock
    OrderProductDao orderProductDao;

    @Mock
    UserServiceImpl userService;

    OrderServiceImpl orderService;

    @Before
    public void setUp() {
        this.orderService = new OrderServiceImpl(orderDao, orderProductDao, userService);
    }

    @Test
    public void placeOrderTest() throws MyException {
        List<OrderProduct> orderProductList = new ArrayList<>();
        Order order = new Order(1L, 1L, OrderStatus.CART.getStatusType(), null, orderProductList);
        String address = "address";
        Long id = userService.getIdOfAuthenticatedUser();
        if (id.equals(order.getUserId())) {
            orderService.placeOrder(address);
        }
        Order currentOrder = new Order(1L, 1L, OrderStatus.PLACED.getStatusType(), "address", orderProductList);
        Order orderInDb = orderDao.getById(1L);
        if (currentOrder.equals(orderInDb)) {
            System.out.println(":) Everything is fine from orderServiceImplTest/placeOrder");
        } else {
            System.out.println(":( something went wrong from orderServiceImplTest/placeOrder");
        }
    }

    @Test
    public void updateCartWithProductListTest() throws MyException {
        OrderProductDto orderProductDto1 = new OrderProductDto(1L, 1L, 1);
        OrderProductDto orderProductDto2 = new OrderProductDto(2L, 1L, 2);
        List<OrderProductDto> orderProductDtoList = null;
        try {
            orderProductDtoList.add(orderProductDto1);
            orderProductDtoList.add(orderProductDto2);
        } catch (NullPointerException e) {
            System.out.println("error");
        }
        Order currentOrder = orderService.updateOrderProductList(orderProductDtoList);
        Order orderInDb = orderDao.getById(1L);
        if (currentOrder.equals(orderInDb)) {
            System.out.println(":) Everything is fine from orderServiceImplTest/updateOrderProductList");
        } else {
            System.out.println(":( something went wrong from orderServiceImplTest/updateOrderProductList");
        }
    }

    @Test
    public void deleteProductInProductListTest() throws MyException {
        OrderProductDto orderProductDto1 = new OrderProductDto(1L, 1L, 1);
        OrderProductDto orderProductDto2 = new OrderProductDto(2L, 1L, 2);
        List<OrderProductDto> orderProductDtoList = null;
        try {
            orderProductDtoList.add(orderProductDto1);
            orderProductDtoList.add(orderProductDto2);
        } catch (NullPointerException e) {
            System.out.println("error");
        }
        Order currentOrder = orderService.updateOrderProductList(orderProductDtoList);
        currentOrder.setOrderId(1L);
        currentOrder.setUserId(1L);
        currentOrder.setOrderStatus(OrderStatus.CART);
        orderService.deleteProductInProductList(2L);
        int checkout = 0;
        List<OrderProduct> productList = currentOrder.getOrderProductList();
        for (OrderProduct i : productList) {
            Long productId = i.getProductId();
            if (productId == 2L) {
                checkout += 1;
            }
        }
        if (checkout == 0) {
            System.out.println(":) Everything is fine from orderServiceImplTest/deleteProductInProductList");
        } else {
            System.out.println(":( something went wrong from orderServiceImplTest/deleteProductInProductList");
        }
    }
}