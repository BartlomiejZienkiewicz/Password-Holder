package packages.PasswordHolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import packages.PasswordHolder.components.InMemoryAuthenticationProvider;
import packages.PasswordHolder.services.impl.InMemoryUserDetailsService;

@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    InMemoryUserDetailsService inMemoryUserDetailsService;

    @Autowired
    InMemoryAuthenticationProvider authenticationProvider;

    public SecurityConfig(InMemoryUserDetailsService inMemoryUserDetailsService) {
        this.inMemoryUserDetailsService = inMemoryUserDetailsService;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(inMemoryUserDetailsService);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/sign_up").permitAll()
                    .antMatchers("/confirm_email").permitAll()
                    .antMatchers("/email_confirmed").permitAll()
                    .antMatchers("/email_confirmation").permitAll()
                    .antMatchers("/AdminPanel").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/MainPage",true)
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("cookies");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
