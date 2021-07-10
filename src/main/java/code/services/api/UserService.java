package code.services.api;

import code.dto.UserDto;
import code.entities.User;

public interface UserService {
    User addUser(UserDto userDto);

    User updateUserInfo(UserDto userDto);

    void logUserIn(String userName, String password);

    void logUserOut();
}
