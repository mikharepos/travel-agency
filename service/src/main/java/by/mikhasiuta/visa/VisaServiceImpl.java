package by.mikhasiuta.visa;

import by.mikhasiuta.model.Visa;
import by.mikhasiuta.repository.VisaRepository;
import by.mikhasiuta.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class VisaServiceImpl implements VisaService {

    private final UserService userService;
    private final VisaRepository visaRepository;

    @Autowired
    public VisaServiceImpl(UserService userService, VisaRepository visaRepository) {
        this.userService = userService;
        this.visaRepository = visaRepository;
    }

    @Override
    public Visa findById(Integer id) {
        return visaRepository.getOne(id);
    }

    @Override
    public List<Visa> findAllVisa() {
        return visaRepository.findAll();
    }

    @Override
    public List<Visa> getVisaCurrentUser() {
        return userService.getCurrentUser().getPassport().getVisas();
    }

    @Override
    public void addVisa(Visa visa) {
        userService.getCurrentUser().getPassport().getVisas().add(visa);
    }
}
