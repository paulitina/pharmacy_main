package code.controllers;

import code.MyException;
import code.dto.UserDto;
import code.entities.User;
import code.services.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/pharmacy/user")
public class UserController {
    private final UserService userService;

    //this is "create user"
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) throws MyException {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @PutMapping("/{userName}")
    public ResponseEntity<User> updateUserInfo(@RequestBody UserDto userDto) throws MyException {
        return ResponseEntity.ok(userService.updateUserInfo(userDto));
    }

    @GetMapping
    public ResponseEntity<UserDto> getAuthenticatedUserDto() {
        return ResponseEntity.ok(userService.getAuthenticatedUserDto());
    }

//    @ExceptionHandler(MyException.class)
//    public ModelAndView handleError(HttpServletRequest req, MyException ex) {
//        log.error("Request: " + req.getRequestURL() + " raised " + ex);
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("exception", ex);
//        mav.addObject("url", req.getRequestURL());
//        mav.setViewName("error");
//        return mav;
//    }
//    @ExceptionHandler( value = {MyException.class})
//    public String logErrors(MyException e) {
//        return "error: " +e.getMessage();
//    }
}
