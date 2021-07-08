package code.services.implementation;

import code.dto.OrderDto;
import code.dto.OrderProductDto;
import code.entities.Order;
import code.entities.OrderProduct;
import code.entities.OrderProductPK;
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

    //Получаем из базы заказ в виде ДТО
    public OrderDto createOrderDto(Order order) {
        return new OrderDto(order.getOrderId(),
                order.getStatus(),
                order.getAddress(),
                createOrderProductDtoList(order.getOrderProductList()));
    }


    //Создаем из бд список товаров ДТО
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

    //создаем пустую корзину
    public Order createEmptyCart(Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderStatus(OrderStatus.CART);
        order.setOrderProductList(new ArrayList<>());
        return orderDao.save(order);
    }

    //Создаем новый элемент корзины
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

    //Получаем из базы заказ в корзине, получаем новые данные о товарах, сохраняем в базе
    @Override
    public void updateOrderProductList(OrderProductDto orderProductDto) {
        Order order = getOrderInCart();
        Long orderId = order.getOrderId();
        int quantity = orderProductDto.getQuantity();
        Long productId = orderProductDto.getProductId();
        OrderProduct orderProduct = orderProductDao.findById(new OrderProductPK(orderId, productId)).get();
        orderProduct.setQuantity(quantity);
        orderProductDao.save(orderProduct);
    }

    //Получаем по айди юзера заказ со статусом корзина/создаем новый, если отсутствует
    public Order getOrderInCart() {
        Order order;
        Long userId = userService.getIdOfAuthenticatedUser();
        List<Order> orders = orderDao.findOrdersByUserIdAndStatus(userId, OrderStatus.CART.getStatusType());
        if (orders.size() == 1) {
            order = orders.get(0);
        } else {
            order = createEmptyCart(userId);
//            order = new Order();
//            order.setUserId(userId);
//            System.out.println("Корзина пустая");
        }
        return order;
    }

    //возвращаем данные корзины из БД в виде ДТО
    @Override
    public OrderDto getUserOrderInCart(){
        Order order = getOrderInCart();
        return createOrderDto(order);
    }

    //Получаем из базы заказ в корзине, меняем статус на "размещен", устанавливаем адрес и пересохраняем
    @Override
    public void placeOrder(String address) {
        Order order = getOrderInCart();
        order.setStatus(OrderStatus.PLACED.getStatusType());
        order.setAddress(address);
        orderDao.save(order);
    }

    //Получаем в виде ДТО все заказы(кроме карт)
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
