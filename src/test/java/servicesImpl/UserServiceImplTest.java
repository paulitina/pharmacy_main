package servicesImpl;

import code.MyException;
import code.dto.UserDto;
import code.entities.User;
import code.repositories.UserDao;
import code.services.implementation.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Mock
    UserDao userDao;

    UserServiceImpl userService;

    @Before
    public void setUp() {
        this.userService = new UserServiceImpl(userDao);
    }

    @Test
    public void addUserTest() throws MyException {
        UserDto userDto = new UserDto(1L, "userName", "email", "password");
        User currentUser = userService.addUser(userDto);
        String passwordHash = currentUser.getPasswordHash();
        String passwordSalt = currentUser.getPasswordSalt();
        currentUser.setType("user");
        User anotherUser = new User(1L, "userName", "email", passwordHash, passwordSalt, "user");
        if (currentUser.equals(anotherUser)) {
            System.out.println(":) Everything is fine from userServiceImplTest/addUser");
        } else {
            System.out.println(":( something went wrong from userServiceImplTest/addUser");
        }
    }

    @Test
    public void updateUserInfoTest() throws MyException {
        User userBeforeUpdate = new User(1L, "userName", "email", "passwordHash", "passwordSalt", "user");
        UserDto userDtoNew = new UserDto(1L, "userName2", "email2", "password2");
        User currentUser = userService.updateUserInfo(userDtoNew);
        String passwordHash = currentUser.getPasswordHash();
        String passwordSalt = currentUser.getPasswordSalt();
        currentUser.setType("user");
        User anotherUser = new User(1L, "userName", "email", passwordHash, passwordSalt, "user");
        if (currentUser.equals(anotherUser)) {
            System.out.println(":) Everything is fine from userServiceImplTest/updateUserInfo");
        } else if (currentUser.equals(userBeforeUpdate)) {
            System.out.println(":( something went wrong from userServiceImplTest/updateUserInfo");
        }
    }
}
