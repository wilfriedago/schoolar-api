package dev.thewlabs.schoolar.common.iam.authentication.repositories;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import dev.thewlabs.schoolar.shared.exceptions.EmailNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);

    default Account loadAccountByEmail(String email) throws EmailNotFoundException {
        return findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
