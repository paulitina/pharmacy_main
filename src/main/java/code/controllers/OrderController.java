package code.controllers;

import code.dto.OrderDto;
import code.dto.OrderProductDto;
import code.entities.Order;
import code.services.implementation.OrderServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pharmacy/order")
public class OrderController {
    OrderServiceImpl orderService;

    @GetMapping("/cart")
    private ResponseEntity<Order> getOrderInCart(){
        return ResponseEntity.ok(orderService.getOrderInCart());
    }

    @GetMapping("/all")
    private ResponseEntity<List<OrderDto>> findOrdersOfUser(){
        return ResponseEntity.ok(orderService.findOrdersOfUser());
    }

    @PutMapping("/cart")
    private void updateOrder(OrderProductDto orderProductDto){
        orderService.updateOrderProductList(orderProductDto);
    }

    @PostMapping
    private void placeOrder(String address){
        orderService.placeOrder(address);
    }
}
