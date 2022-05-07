package packages.PasswordHolder.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.util.Assert;
import packages.PasswordHolder.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;

@Service
public class InMemoryUserDetailsService  implements UserDetailsService {

    private List<UserDetails> users;


    public void addUser(User user){
        user.setEnabled(true);
        users.add(user);
    }


    public InMemoryUserDetailsService() {
        users = new ArrayList<UserDetails>();
        User admin = new User("admin","admin",Arrays.asList("USER","ADMIN"));
        admin.setEnabled(true);
        User user = new User("user1","user1",Arrays.asList("USER"));
        user.setEnabled(true);
        users.add(admin);
        users.add(user);


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
