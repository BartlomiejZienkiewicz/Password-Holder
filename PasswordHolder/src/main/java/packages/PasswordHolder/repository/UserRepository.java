package packages.PasswordHolder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import packages.PasswordHolder.entity.User;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findByConfirmationToken(String Token);


}
