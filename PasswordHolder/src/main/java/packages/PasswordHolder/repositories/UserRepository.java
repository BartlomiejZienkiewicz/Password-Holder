package packages.PasswordHolder.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import packages.PasswordHolder.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
}
