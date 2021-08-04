package code.services.implementation;

import code.MyException;
import code.dto.UserDto;
import code.entities.User;
import code.repositories.UserDao;
import code.services.api.UserService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
@ToString
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    //return user from DB online
    public UserDto createUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword("************");
        return userDto;
    }

    //Получаем из секьрити контекста текущего пользователя
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        String authenticatedUserName = authentication.getName();
        return userDao.findByName(authenticatedUserName);
    }

    //получаем айди текущего пользователя
    public Long getIdOfAuthenticatedUser() {
        User authenticatedUser = getAuthenticatedUser();
        return authenticatedUser.getUserId();
    }

    //получаем Текущего пользователя ДТО
    public UserDto getAuthenticatedUserDto() {
        User user = getAuthenticatedUser();
        return createUserDto(user);
    }

    //Создаем соль для пароля
    public String createPasswordSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String passwordSalt = bytes.toString();
        return passwordSalt;
    }

    //Создаем хэш из пароля и соли
    public String createPasswordHash(String password, String passwordSalt) {
        String passwordWithSalt = password + passwordSalt;
        return DigestUtils.md5Hex(passwordWithSalt);
    }


    //add user from dto user to DB
    @Override
    public User addUser(UserDto userDto) throws MyException {
        User user = new User();
        user.setUserName(userDto.getUserName());
        User userFromDBByName = userDao.findByName(userDto.getUserName());
        if (userFromDBByName != null) {
            throw new MyException("This userName is already taken");
        }
        user.setEmail(userDto.getEmail());
        User userFromDBByEmail = userDao.findByEmail(userDto.getEmail());
        if (userFromDBByEmail != null) {
            throw new MyException("This email is already taken");
        }
        String password = userDto.getPassword();
        String passwordSalt = createPasswordSalt();
        String passwordHash = createPasswordHash(password, passwordSalt);
        String type = "user";
        user.setType(type);
        user.setPasswordHash(passwordHash);
        user.setPasswordSalt(passwordSalt);
        return userDao.save(user);
    }

    //Обновляем с сайта данные о пользователе
    @Override
    public User updateUserInfo(UserDto userDto) {
        User user = userDao.findById(userDto.getUserId()).orElse(null);
        Long uId = userDto.getUserId();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        String password = userDto.getPassword();
        String passwordSalt = createPasswordSalt();
        String passwordHash = createPasswordHash(password, passwordSalt);
        user.setPasswordSalt(passwordSalt);
        user.setPasswordHash(passwordHash);
        return userDao.save(user);
    }
}
