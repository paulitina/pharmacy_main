package code.config;

import code.log.CustomAuthenticationProvider;
import code.repositories.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order(2)
public class WebSecurityFormConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationProvider databaseAuthenticationProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").authenticated();
        http.formLogin().loginPage("/login_page").defaultSuccessUrl("/catalog").loginProcessingUrl("/login")
                .usernameParameter("idUser").passwordParameter("idPassword").permitAll();
//                .
//                and().authenticationProvider(new CustomAuthenticationProvider(userDao));
        http.headers();
    }

//    defaultSuccessUrl("/catalog").

//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build(),
//                User.withDefaultPasswordEncoder().username("user2").password("password").roles("USER").build()
//        );
//    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(databaseAuthenticationProvider);
    }

}
