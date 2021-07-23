package code.services.api;

import code.MyException;
import code.dto.UserDto;
import code.entities.User;

public interface UserService {
    User addUser(UserDto userDto) throws MyException;

    User updateUserInfo(UserDto userDto) throws MyException;

    UserDto getAuthenticatedUserDto();

}
