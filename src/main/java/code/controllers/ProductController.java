package code.controllers;

import code.MyException;
import code.dto.ProductDto;
import code.entities.Product;
import code.services.implementation.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pharmacy/product")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @GetMapping
    private ResponseEntity<List<ProductDto>> getListOfProducts() {
        return ResponseEntity.ok(productServiceImpl.getListOfProducts());
    }

//    @GetMapping
//    public String getListOfProductsView(Model model) {
//        model.addAttribute("listOfProducts", productServiceImpl.getListOfProducts());
//        return "view-listOfProducts";
//    }

    @GetMapping("/{productId}")
    private ResponseEntity<ProductDto> getProductInfo(Long productId) throws MyException {
        return ResponseEntity.ok(productServiceImpl.getProductInfo(productId));
    }

    //    @PostMapping
//    private ResponseEntity<Product> addProduct(ProductDto productDto) throws MyException {
//        return ResponseEntity.ok(productServiceImpl.addProduct(productDto));
//    }

    @GetMapping("/addProduct")
    public String addProductView(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/addProduct")
    public RedirectView addListOfProducts(@ModelAttribute("product") ProductDto productDto,
                                          RedirectAttributes redirectAttributes) throws MyException {
        final RedirectView redirectView = new RedirectView("/pharmacy/product/addProduct", true);
        Product savedProducts = productServiceImpl.addProduct(productDto);
        redirectAttributes.addFlashAttribute("savedProduct", savedProducts);
        redirectAttributes.addFlashAttribute("addProductSuccess", true);
        return redirectView;
    }

    @PutMapping
    private ResponseEntity<Product> updateProduct(ProductDto productDto) throws MyException {
        return ResponseEntity.ok(productServiceImpl.updateProduct(productDto));
    }
}
