package by.mikhasiuta.passport;

import by.mikhasiuta.model.Passport;

import java.sql.Date;

public interface PassportService {

    Passport getPassportCurrentAccount();

    void passportUpdate(Passport passport);

    void passportRegister(Passport passport, Date date);
}
