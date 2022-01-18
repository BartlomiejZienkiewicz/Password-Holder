package packages.PasswordHolder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import packages.PasswordHolder.entities.User;
import packages.PasswordHolder.repositories.PasswordRepository;
import packages.PasswordHolder.repositories.UserRepository;

@SpringBootApplication
@EnableScheduling
public class PasswordHolderApplication {



	public static void main(String[] args) {
		SpringApplication.run(PasswordHolderApplication.class, args);
	}

}
