package services.api;

import entities.Order;

import java.util.List;

public interface OrderService {
    public void placeOrder(String address);
//    public List<Order> findOrdersByStatus(String status);
}
