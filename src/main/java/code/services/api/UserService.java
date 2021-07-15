package code.services.api;

import code.MyException;
import code.dto.UserDto;
import code.entities.User;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface UserService {
    User addUser(UserDto userDto) throws MyException;

    User updateUserInfo(UserDto userDto) throws MyException;

    UserDto getAuthenticatedUserDto();

    void configure(HttpSecurity http) throws Exception

    void logUserIn(String userName, String password);

    void logUserOut();
}
