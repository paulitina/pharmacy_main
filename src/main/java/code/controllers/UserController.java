package code.controllers;

import code.MyException;
import code.dto.UserDto;
import code.entities.User;
import code.services.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pharmacy/user")
public class UserController {
    private final UserService userService;

    //this is "create user"
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(UserDto userDto) throws MyException {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @PutMapping("/{userName}")
    public ResponseEntity<User> updateUserInfo(UserDto userDto) throws MyException {
        return ResponseEntity.ok(userService.updateUserInfo(userDto));
    }

    @GetMapping
    public ResponseEntity<UserDto> getAuthenticatedUserDto() {
        return ResponseEntity.ok(userService.getAuthenticatedUserDto());
    }
}
