package packages.PasswordHolder.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import packages.PasswordHolder.entities.Password;

@Repository
public interface PasswordRepository extends CrudRepository<Password, Integer> {
}
