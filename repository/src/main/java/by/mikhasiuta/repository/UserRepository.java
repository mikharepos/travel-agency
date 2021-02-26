package by.mikhasiuta.repository;

import by.mikhasiuta.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Long>  {

    Account findByLogin(String login);
}
