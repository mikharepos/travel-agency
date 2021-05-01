package by.mikhasiuta.registration;

import by.mikhasiuta.model.Account;
import by.mikhasiuta.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrationService {

    private final UserService accountService;
    private final PasswordEncoder encoder;

    @Autowired
    public RegistrationService(UserService accountService, PasswordEncoder encoder) {
        this.accountService = accountService;
        this.encoder = encoder;
    }

    public void registerUser(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));
        accountService.saveAccount(account);
    }
}
