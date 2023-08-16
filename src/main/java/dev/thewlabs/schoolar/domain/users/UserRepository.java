package dev.thewlabs.schoolar.domain.users;

import dev.thewlabs.schoolar.core.interfaces.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User> {
}
