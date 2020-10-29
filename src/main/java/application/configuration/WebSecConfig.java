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

import javax.persistence.EntityManager;

@Configuration
@EnableWebSecurity
public class WebSecConfig extends WebSecurityConfigurerAdapter {
    @Autowired
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
                //TODO csrf tokenS
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .loginProcessingUrl("/loginProcess")
                //TODO " " to "/index"
//              .defaultSuccessUrl(" ")
//              .failureUrl("/login?error=true")

                .and()

                //TODO logout

                .authorizeRequests()
                //for notAUTHENTICATED users these requests are enabled:
                .antMatchers("/login","/registration","/createNewUser","/loginProcess")
                .permitAll()
                //for AUTHENTICATED users ALL request are enabled:
                .anyRequest()
                .authenticated();

        //                .antMatchers("/**")
//                .access("hasAuthority('USER')")




        ;
    }

}
