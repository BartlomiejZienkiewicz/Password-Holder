package packages.PasswordHolder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import packages.PasswordHolder.entity.Password;

@Repository
public interface PasswordRepository extends CrudRepository<Password, Long> {
}
