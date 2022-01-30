package packages.PasswordHolder.services;

import packages.PasswordHolder.entities.User;

public interface SignUpService {

    User signUpUser(User user) throws Exception;
}
