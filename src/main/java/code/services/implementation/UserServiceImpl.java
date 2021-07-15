package code.services.implementation;

import code.MyException;
import code.dto.UserDto;
import code.entities.User;
import code.repositories.UserDao;
import code.services.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.logging.Filter;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
//    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserDao userDao;

    //return user from DB online
    public UserDto createUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
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
        Integer passwordIntHash = password.hashCode();
        String passwordHash = passwordSalt + passwordIntHash;
        return passwordHash;
    }

    //add user from dto user to DB
    @Override
    public User addUser(UserDto userDto) throws MyException {
        User user = new User();
        user.setUserName(userDto.getUserName());
        User userFromDBByName = userDao.findByName(userDto.getUserName());
        if (userFromDBByName != null) {
            return null;
        }
        user.setEmail(userDto.getEmail());
        User userFromDBByEmail = userDao.findByEmail(userDto.getEmail());
        if (userFromDBByEmail != null) {
            return null;
        }
        String password = userDto.getPassword();
        String passwordSalt = createPasswordSalt();
        String passwordHash = createPasswordHash(password, passwordSalt);
        String type = "buyer";
        user.setType(type);
        user.setPasswordHash(passwordHash);
        user.setPasswordSalt(passwordSalt);
        return userDao.save(user);
    }

    //Обновляем с сайта данные о пользователе
    @Override
    public User updateUserInfo(UserDto userDto) throws MyException {
        User user = userDao.findById(userDto.getUserId()).get();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        String password = userDto.getPassword();
        String passwordSalt = createPasswordSalt();
        String passwordHash = createPasswordHash(password, passwordSalt);
        user.setPasswordSalt(passwordSalt);
        user.setPasswordHash(passwordHash);
        return userDao.save(user);
    }

    @Bean
    public Filter ajaxTimeOutRedirectFilter() {
        AjaxTimeOutRedirectFilter f = new AjaxTimeOutRedirectFilter();
        return f;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").authenticated();
        http.formLogin().loginPage("/login.jsp").loginProcessingUrl("/login")
                .successHandler(new CustomAuthenticationSuccessHandler())
                .usernameParameter("idUser").passwordParameter("idPassword").permitAll();
        http.logout().logoutSuccessHandler(loggingLogoutSuccessHandler).permitAll();
        http.headers();
        http.addFilterAfter(ajaxTimeOutRedirectFilter(), ExceptionTranslationFilter.class);
    }

    @Override
//    @Query("SELECT u FROM users WHERE ((user_name = ?)  AND (password_hash = ?))")
    public void logUserIn(String userName, String password) {
//        @Param("user_name") String passwordSalt = ;
//        String passwordHash = password.toString() + passwordSalt;
//        List<User> users = userDao.findAll();
//        for (int i : users) {
//            if users.get()
//        }
    }

    @Override
    public void logUserOut() {

    }

}
