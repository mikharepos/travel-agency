package by.mikhasiuta.passport;

import by.mikhasiuta.model.Account;
import by.mikhasiuta.model.Passport;
import by.mikhasiuta.repository.PassportRepository;
import by.mikhasiuta.user.UserService;
import by.mikhasiuta.user.UserServiceImpl;
import by.mikhasiuta.visa.VisaService;
import by.mikhasiuta.visa.VisaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;

@Service
@Transactional
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;
    private final UserService userService;
    private final VisaService visaService;

    @Autowired
    public PassportServiceImpl(PassportRepository passportRepository,
                               UserService userService, VisaService visaService) {
        this.passportRepository = passportRepository;
        this.userService = userService;
        this.visaService = visaService;
    }

    @Override
    public Passport getPassportCurrentAccount() {
        return userService.getCurrentUser().getPassport();
    }

    @Override
    public void passportUpdate(Passport passport) {
        getPassportCurrentAccount().setFirstName(passport.getFirstName());
        getPassportCurrentAccount().setLastName(passport.getLastName());
        getPassportCurrentAccount().setPassportSeries(passport.getPassportSeries());
        getPassportCurrentAccount().setPassportNumber(passport.getPassportNumber());
        getPassportCurrentAccount().setBirthDate(passport.getBirthDate());
        getPassportCurrentAccount().setVisas(visaService.getVisaCurrentUser());
    }

    @Override
    public void passportRegister(Passport passport, Date date) {
        Account account = userService.getCurrentUser();
        passport.setBirthDate(date);
        passportRepository.save(passport);
        account.setPassport(passport);
        userService.saveAccount(account);
    }
}
