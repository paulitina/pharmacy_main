package code;

import code.dto.UserDto;
import code.entities.User;
import code.repositories.UserDao;
import code.services.api.UserService;
import code.services.implementation.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;



import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserServiceImplTest {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);

    @Autowired
    private UserDao userDao;

    private UserServiceImpl userService;

    @Before
    public void setUp() {
        this.userService = new UserServiceImpl(userDao);
    }

    @Test
    @WithMockUser(username = "userName")
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void whenUserDtoAddUserThenSuccessUserAddedToDBTest() throws MyException {
        UserDto userDto = new UserDto(2L, "userName2", "email2", "password");
        User returnUser = userService.addUser(userDto);
        log.info("{}", returnUser);
        String passwordHash = returnUser.getPasswordHash();
        String passwordSalt = returnUser.getPasswordSalt();
        User actualUser = userDao.findById(2L).get();
        User expectedUser = new User(2L, "userName2", "email2", passwordHash, passwordSalt, "user");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    @Sql(value = {"/create-user-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void whenUserDtoUpdateUserInfoThenSuccessInfoAboutUserInDBIsUpdatesTest() throws MyException {
        UserDto userDtoNew = new UserDto(1L, "userName2", "email2", "password2");
        User returnUser = userService.updateUserInfo(userDtoNew);
        String passwordHash = returnUser.getPasswordHash();
        String passwordSalt = returnUser.getPasswordSalt();
        returnUser.setType("user");
        User actualUser = userDao.findById(1L).get();
        User expectedUser = new User(1L, "userName2", "email2", passwordHash, passwordSalt, "user");
        assertEquals(expectedUser, actualUser);
    }
}
