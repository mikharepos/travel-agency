package by.mikhasiuta.visa;

import by.mikhasiuta.model.Visa;

import java.util.List;

public interface VisaService {

    Visa findById(Integer id);

    List<Visa> findAllVisa();

    List<Visa> getVisaCurrentUser();

    void addVisa(Visa visa);
}
