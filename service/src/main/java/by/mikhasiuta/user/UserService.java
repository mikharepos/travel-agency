package by.mikhasiuta.user;

import by.mikhasiuta.model.Account;

public interface UserService {

    void saveAccount(Account account);

    Account getCurrentUser();

}
