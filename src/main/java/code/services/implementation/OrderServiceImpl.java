package code.services.implementation;

import code.dto.OrderDto;
import code.dto.OrderProductDto;
import code.entities.Order;
import code.entities.OrderProduct;
import code.enums.OrderStatus;
import code.repositories.OrderDao;
import code.repositories.OrderProductDao;
import code.services.api.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao;

    OrderProductDao orderProductDao;

    UserServiceImpl userService;

    public OrderDto createOrderDto(Order order) {
        return new OrderDto(order.getOrderId(),
                order.getStatus(),
                order.getAddress(),
                createOrderProductDtoList(order.getOrderProductList()));
    }

    public List<OrderProductDto> createOrderProductDtoList(List<OrderProduct> orderProducts) {
        if (orderProducts == null) {
            return new ArrayList<>();
        }
        List<OrderProductDto> orderProductDtoList = orderProducts.stream()
                .map(orderProduct -> new OrderProductDto(orderProduct.getProductId(),
                        orderProduct.getOrderId(),
                        orderProduct.getQuantity()))
                .collect(Collectors.toList());
        return orderProductDtoList;
    }


    public Order createEmptyCart(Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderStatus(OrderStatus.CART);
        order.setOrderProductList(new ArrayList<>());
        return orderDao.save(order);
    }

    public void createCartProduct(Long productId) {
        Long userId = userService.getIdOfAuthenticatedUser();
        Order orderInCart;
        List<Order> orders = orderDao.findOrdersByUserIdAndStatus(userId, OrderStatus.CART.getStatusType());
        if (orders.isEmpty()) {
            orderInCart = createEmptyCart(userId);
        }else {
            orderInCart = orders.get(0);
        }
        int quantity = 1;
        OrderProduct orderProduct = new OrderProduct(productId, orderInCart.getOrderId(), quantity);
        orderProductDao.save(orderProduct);

    }

    @Override
    public Order updateOrderProductList(OrderDto orderDto) {
        Order order = getOrderInCart();

//        product.setName(productDto.getName());
//        product.setIndications(productDto.getIndications());
//        product.setSideEffects(productDto.getSideEffects());
//        product.setManufacturerInfo(productDto.getManufacturerInfo());
//        product.setQuantity(productDto.getQuantity());
//        product.setPrice(product.getPrice());
//        product.setPrescribed(productDto.getPrescribed());
//        product.setImage(productDto.getImage());
        return order;
    }

    /////////
    @Override
    public OrderDto getOrderInCart() {
        Order order;
        Long userId = userService.getIdOfAuthenticatedUser();
        List<Order> orders = orderDao.findOrdersByUserIdAndStatus(userId, OrderStatus.CART.getStatusType());
        if (orders.size() == 1) {
            order = orders.get(0);
        } else {
            order = new Order();
        }
        return createOrderDto(order);
    }

    @Override
    public void placeOrder(String address) {
        Order order = getOrderInCart();
        order.setStatus(OrderStatus.PLACED.getStatusType());
        order.setAddress(address);
        orderDao.save(order);
    }

    @Override
    public List<OrderDto> findOrdersOfUser() {
        Long userId = userService.getIdOfAuthenticatedUser();
        List<Order> orders = orderDao.findOrdersByUserIdAndNotStatus(userId, OrderStatus.CART.getStatusType());
        List<OrderDto> ordersOfUser = orders.stream()
                .map(this::createOrderDto)
                .collect(Collectors.toList());
        return ordersOfUser;
    }
}
