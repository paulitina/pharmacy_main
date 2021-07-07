package code.services.api;

import code.dto.OrderDto;
import code.entities.Order;

import java.util.List;

public interface OrderService {
    //Найти текущего юзера, Получить его заказ в корзине, проверить,
    //что он не пустой, получить адрес и поменять статус
    void placeOrder(String address);

    //Получить текущего юзера, по его айди найти все его заказы
    List<OrderDto> findOrdersOfUser();

    //Получить ордерДТО() и список товаров()
    //Создать пустую корзину, если пустая есть, то получить данные и поменять все из ДТО в корзине в базе
    Order updateOrderProductList(OrderDto orderDto);

    //Получить заказ со статусом корзина
    OrderDto getOrderInCart();
}
