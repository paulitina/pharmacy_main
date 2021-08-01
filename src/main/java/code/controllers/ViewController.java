package code.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("user_orders")
    public String getUserOrders() {
        return "user_orders";
    }

    @GetMapping("/logout_page")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login_page";
    }
}
