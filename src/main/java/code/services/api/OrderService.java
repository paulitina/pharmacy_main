package code.services.api;

import code.MyException;
import code.dto.OrderDto;
import code.dto.OrderProductDto;
import code.entities.Order;

import java.util.List;

public interface OrderService {
    //Найти текущего юзера, Получить его заказ в корзине, проверить,
    //что он не пустой, получить адрес и поменять статус
    void placeOrder(String address) throws MyException;

    //Получить текущего юзера, по его айди найти все его заказы
    List<OrderDto> findOrdersOfUser() throws MyException;

    //Удаляем продукт из корзины
    void deleteProductInProductList(Long productId) throws MyException;

    //Создаем новый продукт в заказе
    void createCartProduct(Long productId) throws MyException;

    //Получить ордерДТО() и список товаров()
    //Создать пустую корзину, если пустая есть, то получить данные и поменять все из ДТО в корзине в базе
    Order updateOrderProductList(List<OrderProductDto> orderProductDtoList) throws MyException;

    //Получить заказ со статусом корзина
    OrderDto getUserOrderInCart() throws MyException;
}
