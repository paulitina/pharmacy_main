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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    private final OrderProductDao orderProductDao;

    private final UserServiceImpl userService;

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
        return orderProducts.stream()
                .map(orderProduct -> new OrderProductDto(orderProduct.getOrderId(), orderProduct.getProductId(),
                        orderProduct.getQuantity()))
                .collect(Collectors.toList());
    }

    //создаем пустую корзину
    public Order createEmptyCart(Long userId) {
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.CART.getStatusType());
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
        } else {
            orderInCart = orders.get(0);
        }
        Integer quantity = 1;
        OrderProduct orderProduct = new OrderProduct(orderInCart.getOrderId(), productId, quantity);
        orderProductDao.save(orderProduct);
    }

    // Удаляем товар из заказа
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteProductInProductList(Long productId) {
        Order orderInCart = getOrderInCart();
        Long orderId = orderInCart.getOrderId();
        OrderProduct orderProduct = orderProductDao.findById(new OrderProductPK(orderId, productId)).orElse(null);
        log.info("{}", orderProduct);
        List<OrderProduct> orderProductList = orderInCart.getOrderProductList();
        orderProductList.remove(orderProduct);
        orderProductDao.delete(orderProduct);
    }

    //Получаем из базы заказ в корзине, получаем новые данные о товарах, сохраняем в базе
    @Override
    public Order updateOrderProductList(List<OrderProductDto> orderProductDtoList) {
        Order order = getOrderInCart();
        Long orderId = order.getOrderId();
        for (OrderProductDto i : orderProductDtoList) {
            Integer quantity = i.getQuantity();
            Long productId = i.getProductId();
            OrderProduct orderProduct = orderProductDao.findById(new OrderProductPK(orderId, productId)).orElse(null);
            orderProduct.setQuantity(quantity);
            orderProductDao.save(orderProduct);
        }
//        order.getOrderProductList();
        return order;
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
        }
        return order;
    }

    //возвращаем данные корзины из БД в виде ДТО
    @Override
    public OrderDto getUserOrderInCart() {
        Order order = getOrderInCart();
        return createOrderDto(order);
    }

    public List<OrderProductDto> getListOfOrdersInCart() {
        OrderDto order = getUserOrderInCart();
        return order.getProductDtoList();

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
