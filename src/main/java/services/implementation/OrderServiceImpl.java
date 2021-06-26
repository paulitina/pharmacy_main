package services.implementation;

import entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.OrderDao;
import services.api.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public void placeOrder(String address) {
        String status = "placed";
        orderDao.save(new Order(??, ??, status, address));
    }

    @Override
    public List<Order> findOrdersByStatus(String status) {
        return orderDao.findAllById(??);
    }
}
