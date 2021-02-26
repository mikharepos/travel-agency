package by.mikhasiuta.wallet;

import by.mikhasiuta.model.Wallet;

public interface WalletService {

    Wallet getWalletCurrentUser();

    void walletSave(Wallet wallet);

    void addMoney(String money);
}
