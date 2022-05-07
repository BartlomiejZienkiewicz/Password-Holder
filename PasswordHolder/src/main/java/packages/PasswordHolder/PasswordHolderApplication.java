package packages.PasswordHolder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PasswordHolderApplication {



	public static void main(String[] args) {
		SpringApplication.run(PasswordHolderApplication.class, args);
	}

}
