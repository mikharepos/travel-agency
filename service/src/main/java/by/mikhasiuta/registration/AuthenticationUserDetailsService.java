package by.mikhasiuta.registration;

import by.mikhasiuta.repository.UserRepository;
import by.mikhasiuta.exception.UserNotFoundException;
import by.mikhasiuta.model.Account;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthenticationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws InternalAuthenticationServiceException {
       Account account = userRepository.findByLogin(userName);
        if (account == null) {
            throw new UserNotFoundException("Unknown user: "+userName);
        }
        return User.builder()
                .username(account.getLogin())
                .password(account.getPassword())
                .roles(account.getRole().name())
                .build();
    }
}
