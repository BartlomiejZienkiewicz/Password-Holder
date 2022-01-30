package packages.PasswordHolder.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import packages.PasswordHolder.components.mailer.RandomStringFactory;
import packages.PasswordHolder.components.mailer.SignUpMailTextFactory;
import packages.PasswordHolder.components.mailer.SignUpMailer;
import packages.PasswordHolder.entities.User;
import packages.PasswordHolder.repositories.UserRepository;
import packages.PasswordHolder.services.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private InMemoryUserDetailsService userDetails;

    @Autowired
    private SignUpMailer signUpMailer;
    private static final int TOKEN_LENGTH = 15;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUpUser(User user){
        Assert.isNull(user.getUserId(), "Can't sign up given user, it already has set id. User: " + user.toString());
        String usernameOfSigningUser = user.getUsername();

        String p = passwordEncoder.encode(user.getPassword());
        user.setPassword(p);

        String token = RandomStringFactory.getRandomString(TOKEN_LENGTH);


        user.setConfirmationToken(token);

        user.setConfirmationToken(token);
        signUpMailer.sendConfirmationLink(user.getEmail(),token);
        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
