package code.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String hello(){
        return "for everyone";
    }

    @GetMapping("/user")
    public String user(){
        return "for user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "for admin";
    }
}
