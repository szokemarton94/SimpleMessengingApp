package application.configuration;

import application.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter {

    @PersistenceContext
    EntityManager entityManager;
    

    /**
     * Encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//LOGIN
                //TODO csrf token
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/loginProcess")
                .defaultSuccessUrl("/index", true)

                .and()
//LOGOUT
                //TODO make it POST, this is vulnerable to csrf
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/index")
//                .deleteCookies("remove")
//                .invalidateHttpSession(true)
//                .logoutUrl("/logout")//
//                .logoutSuccessUrl("/login")

                .and()
//AUTHORIZATION
                .authorizeRequests()
                //for notAUTHENTICATED users these requests are enabled:
                .antMatchers("/index", "/login", "/registration", "/createNewUser", "/loginProcess", "/resources/static/**", "/logoutSuccess", "/customLogout")
                .permitAll()
                //for AUTHENTICATED users ALL request are enabled:
                .anyRequest()
                .authenticated();
        //                .antMatchers("/**")
        //                .access("hasAuthority('USER')")
    }

}
