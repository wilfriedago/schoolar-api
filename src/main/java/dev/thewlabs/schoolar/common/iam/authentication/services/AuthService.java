package dev.thewlabs.schoolar.common.iam.authentication.services;

import dev.thewlabs.schoolar.common.iam.authentication.entities.Account;
import dev.thewlabs.schoolar.common.iam.authentication.repositories.AccountRepository;
import dev.thewlabs.schoolar.shared.exceptions.EmailNotFoundException;
import dev.thewlabs.schoolar.shared.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final AccountRepository accountRepository;

    @Autowired
    public AuthService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void check(Account accountToCheck) {

    }

    public Account updatePassword(Account account, String newPassword) {
        return null;
    }

    @Transactional
    public Account createAccount(String email) {
        String password = PasswordUtils.generate();

        Account account = new Account();

        account.setEmail(email);
        account.setPassword(PasswordUtils.encode(password));

        accountRepository.save(account);

        return account;
    }

    public void updateAccount(Account account) {

    }

    public void deleteAccount(String email) {

    }

    public void changePassword(String oldPassword, String newPassword) {

    }

    public boolean existsByEmail(String email) {
        return this.accountRepository.existsByEmail(email);
    }

    public Account loadAccountByEmail(String email) throws EmailNotFoundException {
        return this.accountRepository.loadAccountByEmail(email);
    }
}
