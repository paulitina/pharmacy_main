//package code.config;
//
//import code.log.CustomAuthenticationProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//@Order(1)
//public class WebSecurityBasicConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomAuthenticationProvider databaseAuthenticationProvider;
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.httpBasic();
//        http.authorizeRequests().antMatchers("/api/**").authenticated();
//        http.headers();
//    }
//
//    @Autowired
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(databaseAuthenticationProvider);
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
////                .antMatchers("/admin").hasRole("ADMIN")
////                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
////                .antMatchers("/hello").permitAll()
////                .and().formLogin();
////    }
//
//}
