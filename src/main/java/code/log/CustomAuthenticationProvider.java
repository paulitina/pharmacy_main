package code.log;

import code.entities.User;
import code.repositories.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDao userDao;

    @Override
    public UsernamePasswordAuthenticationToken authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User userInDBByName = userDao.findByName(name);
        System.out.println(userInDBByName);
        log.info(String.valueOf(userInDBByName));
        String nameInDB = userInDBByName.getUserName();
        if (userInDBByName != null) {
            String salt = userInDBByName.getPasswordSalt();
            String authPasswordWithSalt = password + salt;
            String authPasswordHash = DigestUtils.md5Hex(authPasswordWithSalt);
            String passwordInDb = userInDBByName.getPasswordHash();
            if (authPasswordHash.equals(passwordInDb)){
                return new UsernamePasswordAuthenticationToken(
                        nameInDB, password, new ArrayList<>());
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
