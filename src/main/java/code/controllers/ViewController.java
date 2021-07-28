package code.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {


    @GetMapping("product")
    public String getProductView() {
        return "product";
    }

    @GetMapping("product/{productId}")
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

//    @GetMapping("login.jsp")
//    public String getLoginJSPView() {
//        return "login";
//    }
}
