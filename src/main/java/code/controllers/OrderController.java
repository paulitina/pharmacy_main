package code.controllers;

import code.MyException;
import code.dto.OrderDto;
import code.dto.OrderProductDto;
import code.entities.Order;
import code.entities.OrderProduct;
import code.services.implementation.OrderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pharmacy/order")
public class OrderController {
    private final OrderServiceImpl orderService;

    //    @GetMapping("cart")
//    private ResponseEntity<OrderDto> getUserOrderInCart() throws MyException {
//        return ResponseEntity.ok(orderService.getUserOrderInCart());
//    }

    @GetMapping("cart")
    public ResponseEntity<List<OrderProductDto>> getListOfOrdersInCart(){
        return ResponseEntity.ok(orderService.getListOfOrdersInCart());
    }

    @GetMapping("/all")
    private ResponseEntity<List<OrderDto>> findOrdersOfUser() {
        return ResponseEntity.ok(orderService.findOrdersOfUser());
    }

    @PutMapping("/cart")
    private ResponseEntity<Order> updateOrder(@RequestBody List<OrderProductDto> orderProductDtoList) {
        return ResponseEntity.ok(orderService.updateOrderProductList(orderProductDtoList));
    }

    @PostMapping("/cart")
    private void deleteProductInProductList(@RequestBody Long productId) {
        orderService.deleteProductInProductList(productId);
    }

//
//    @PostMapping("/cart")
//    private void createCartProduct(Long productId) throws MyException {
//        orderService.createCartProduct(productId);
//    }

    @PostMapping
    private void placeOrder(@RequestBody String address) throws MyException {
        orderService.placeOrder(address);
    }
}
