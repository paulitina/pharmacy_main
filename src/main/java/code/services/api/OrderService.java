package code.services.api;

import code.dto.OrderDto;
import code.dto.OrderProductDto;

import java.util.List;

public interface OrderService {
    //Найти текущего юзера, Получить его заказ в корзине, проверить,
    //что он не пустой, получить адрес и поменять статус
    void placeOrder(String address);

    //Получить текущего юзера, по его айди найти все его заказы
    List<OrderDto> findOrdersOfUser();

    //Получить ордерДТО() и список товаров()
    //Создать пустую корзину, если пустая есть, то получить данные и поменять все из ДТО в корзине в базе
    void updateOrderProductList(OrderProductDto orderProductDto);

    //Получить заказ со статусом корзина
    OrderDto getUserOrderInCart();
}
