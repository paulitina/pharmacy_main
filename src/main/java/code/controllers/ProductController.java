package code.controllers;

import code.MyException;
import code.dto.ProductDto;
import code.entities.Product;
import code.services.implementation.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacy/product")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @GetMapping
    private ResponseEntity<List<ProductDto>> getListOfProducts() {
        return ResponseEntity.ok(productServiceImpl.getListOfProducts());
    }

    @GetMapping("/{productId}")
    private ResponseEntity<ProductDto> getProductInfo(Long productId) throws MyException {
        return ResponseEntity.ok(productServiceImpl.getProductInfo(productId));
    }

    @PostMapping
    private ResponseEntity<Product> addProduct(ProductDto productDto) throws MyException {
        return ResponseEntity.ok(productServiceImpl.addProduct(productDto));
    }

    @PutMapping
    private ResponseEntity<Product> updateProduct(ProductDto productDto) throws MyException {
        return ResponseEntity.ok(productServiceImpl.updateProduct(productDto));
    }
}
