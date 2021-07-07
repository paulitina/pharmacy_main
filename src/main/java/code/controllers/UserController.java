package code.controllers;

import code.dto.UserDto;
import code.entities.User;
import code.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pharmacy/user")
public class UserController {
    UserService userService;

    //this is "create user"
    @PostMapping
    public ResponseEntity<User> addUser(UserDto userDto) {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @PutMapping("/{username}")
    private ResponseEntity<User> updateUserInfo(UserDto userDto){
        return ResponseEntity.ok(userService.updateUserInfo(userDto));
    }
}
