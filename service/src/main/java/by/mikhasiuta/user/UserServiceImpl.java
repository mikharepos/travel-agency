package by.mikhasiuta.user;

import by.mikhasiuta.model.*;
import by.mikhasiuta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveAccount(Account account) {
        userRepository.save(account);
    }

    @Override
    public Account getCurrentUser() {
        return userRepository.findByLogin(getNameCurrentUser());
    }

    public String getNameCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
