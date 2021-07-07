package code.services.implementation;

import code.dto.UserDto;
import code.entities.User;
import code.repositories.UserDao;
import code.services.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.security.SecureRandom;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    UserDao userDao;

    //return user from DB online
    public UserDto createUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        return  userDto;
    }

    public Long getIdOfAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authenticatedUserName = authentication.getName();
        User authenticatedUser = userDao.findByName(authenticatedUserName);
        return authenticatedUser.getUserId();
    }

    //add user from dto user to DB
    @Override
    public User addUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        String password = userDto.getPassword();
        Integer passwordIntHash = password.hashCode();
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String passwordSalt = bytes.toString();
        String passwordHash = passwordIntHash + passwordSalt;
        String type = "buyer";
        user.setType(type);
        user.setPasswordHash(passwordHash);
        user.setPasswordSalt(passwordSalt);
        return user;
    }

    @Override
    public User updateUserInfo(UserDto userDto) {
        User user = userDao.findById(userDto.getUserId()).get();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        return user;
    }

    @Override
//    @Query("SELECT u FROM users WHERE ((user_name = ?)  AND (password_hash = ?))")
    public void logUserIn(String userName, String password) {
//        @Param("user_name") String passwordSalt = ;
        String passwordHash = password.toString() + passwordSalt;
//        List<User> users = userDao.findAll();
//        for (int i : users) {
//            if users.get()
//        }
    }

    @Override
    public void logUserOut() {

    }

}
