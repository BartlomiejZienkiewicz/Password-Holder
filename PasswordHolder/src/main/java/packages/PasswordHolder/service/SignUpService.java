package packages.PasswordHolder.service;

import packages.PasswordHolder.entity.User;

public interface SignUpService {

    User signUpUser(User user) throws Exception;
}
