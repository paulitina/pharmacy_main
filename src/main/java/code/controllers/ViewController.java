package code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {


    @GetMapping("catalog")
    public String getProductView() {
        return "catalog";
    }

    @GetMapping("product")
    public String getProductInfoView() {
        return "product_page";
    }

    @GetMapping("cart")
    public String getOrderInCart() {
        return "cart";
    }

    @GetMapping("account")
    public String getUserInfo() {
        return "account";
    }

    @GetMapping("registration")
    public String getRegistration() {
        return "registration";
    }
}
