package by.mikhasiuta.bank;

import by.mikhasiuta.model.Bank;
import by.mikhasiuta.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    @Autowired
    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public Bank getBank(Integer id) {
        return bankRepository.getOne(id);
    }
}
