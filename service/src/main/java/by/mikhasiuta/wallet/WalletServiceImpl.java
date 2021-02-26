package by.mikhasiuta.wallet;

import by.mikhasiuta.model.Account;
import by.mikhasiuta.model.Wallet;
import by.mikhasiuta.repository.WalletRepository;
import by.mikhasiuta.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserService userService;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
    }

    @Override
    public Wallet getWalletCurrentUser() {
        return userService.getCurrentUser().getWallet();
    }

    @Override
    public void walletSave(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Override
    public void addMoney(String money) {
        Account account = userService.getCurrentUser();
        double amountInWallet = account.getWallet().getAmount();
        account.getWallet().setAmount(amountInWallet + Double.parseDouble(money));
        userService.saveAccount(account);
    }
}
