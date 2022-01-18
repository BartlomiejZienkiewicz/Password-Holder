package packages.PasswordHolder.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.util.Assert;
import packages.PasswordHolder.entities.Password;
import packages.PasswordHolder.entities.User;
import packages.PasswordHolder.repositories.PasswordRepository;
import packages.PasswordHolder.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

@Service
public class InMemoryUserDetailsService  implements UserDetailsService {

    private List<UserDetails> users;


    public InMemoryUserDetailsService() {
        users = new ArrayList<UserDetails>();
        users.add(new User("user1","user1",Arrays.asList("USER")));
        users.add(new User("user2","user2",Arrays.asList("USER")));
        users.add(new User("admin","admin",Arrays.asList("USER","ADMIN")));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username,"Username cannot be null");
        Optional <UserDetails> user =
                users.stream().filter(userDetails -> username.equals(userDetails.getUsername())).findFirst();
        if(!user.isPresent()){
            throw new UsernameNotFoundException("Username not found");
        }
        return user.get();
    }

    public List<UserDetails> getUsers() {
        return users;
    }
}
