package services.implementation;

import entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserDao;
import services.api.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public void addUser(String userName, String email, String passwordHash, String passwordSalt, String type){
        userDao.save(new User(userName, email, passwordHash, passwordSalt, type));
    };

    @Override
    public void updateUserInfo(String... args){}

    @Override
    public void logUserIn(String userName, String password){
    }

    @Override
    public void logUserOut(){};

}
