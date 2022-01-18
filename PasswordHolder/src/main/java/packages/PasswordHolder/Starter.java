package packages.PasswordHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import packages.PasswordHolder.entities.Password;
import packages.PasswordHolder.entities.User;
import packages.PasswordHolder.repositories.PasswordRepository;
import packages.PasswordHolder.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Component
public class Starter implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordRepository passwordRepository;



    @Override
    public void run(String... args) throws Exception {
        Password password1 = new Password();
        Password password2 = new Password();
        Password password3 = new Password();

        password1.setPassword("PasswordOfUser1");
        password1.setDescription("user1");
        password1.setNameOfOwner("user1");

        password2.setPassword("PasswordOfUser2");
        password2.setDescription("user2");
        password2.setNameOfOwner("user2");

        password3.setPassword("PasswordOfUser3");
        password3.setDescription("user3");
        password3.setNameOfOwner("user3");

        passwordRepository.save(password1);
        passwordRepository.save(password2);
        passwordRepository.save(password3);

    }
}
