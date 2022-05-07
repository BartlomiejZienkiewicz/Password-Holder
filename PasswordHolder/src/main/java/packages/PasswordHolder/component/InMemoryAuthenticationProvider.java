package packages.PasswordHolder.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import packages.PasswordHolder.entity.User;
import packages.PasswordHolder.service.impl.InMemoryUserDetailsService;

@Component
public class InMemoryAuthenticationProvider implements AuthenticationProvider {


    private InMemoryUserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;


    @Autowired
    public InMemoryAuthenticationProvider(@Lazy PasswordEncoder passwordEncoder, InMemoryUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        Object credentials = authentication.getCredentials();
        Assert.notNull(name, "Name cannot be null");
        Assert.notNull(credentials, "Credentials cannot be null");

        if(!(credentials instanceof String)){
            return null;
        }


        String password = credentials.toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(name);
        boolean passwordMatch = passwordEncoder.matches(password, userDetails.getPassword());

        if(!password.equals(userDetails.getPassword()) ){
            if(!passwordMatch){
                throw new BadCredentialsException("Wrong password");
            }

        }
        User user = (User) userDetailsService.loadUserByUsername(name);
        if(user.isEnabled() == false){
            return null;
        }


        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(name, password, userDetails.getAuthorities());

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
