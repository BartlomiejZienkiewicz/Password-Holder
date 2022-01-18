package packages.PasswordHolder.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class InMemoryAuthenticationProvider implements AuthenticationProvider {

    UserDetailsService userDetailsService;

    @Autowired
    public InMemoryAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;

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

        if(!password.equals(userDetails.getPassword())){
            throw new BadCredentialsException("Wrong password");
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
