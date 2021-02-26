package by.mikhasiuta.basket;

import by.mikhasiuta.bank.BankService;
import by.mikhasiuta.model.*;
import by.mikhasiuta.repository.BasketRepository;
import by.mikhasiuta.tour.TourService;
import by.mikhasiuta.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class BasketIServiceImpl implements BasketService {

    private final BasketRepository BasketRepository;
    private final TourService tourService;
    private final UserService userService;
    private final BankService bankService;

    @Autowired
    public BasketIServiceImpl(BasketRepository basketRepository,
                              TourService tourService, UserService userService,
                              BankService bankService) {
        this.BasketRepository = basketRepository;
        this.tourService = tourService;
        this.userService = userService;
        this.bankService = bankService;
    }

    @Override
    public void basketSave(Basket basket) {
        BasketRepository.save(basket);
    }

    @Override
    public void addTourInBasket(Integer id){
        Tour tour = tourService.findById(id);
        tourService.saveTour(tour);
        userService.getCurrentUser().getBasket().getTours().add(tour);
    }

    @Override
    public void deleteTourInBasket(Integer id) {
        Tour tour = tourService.findById(id);
        userService.getCurrentUser().getBasket().getTours().remove(tour);
    }

    @Override
    public void paymentService() {
        double totalCost = 0;
        double totalCost1 = 0;
        Account account = userService.getCurrentUser();
        List<Tour> toursList = account.getBasket().getTours();
        for (Tour tour:toursList){
            if (tour.getStatusOfTheTour() != StatusOfTheTour.NORMAL) {
                totalCost1 += (tour.getCost() - (tour.getCost()*20/100));
            }
            if (tour.getStatusOfTheTour() == StatusOfTheTour.NORMAL) {
               totalCost += tour.getCost();
            }
      }
        double totalPrice = totalCost + totalCost1;

        Wallet wallet = account.getWallet();
        double amount = wallet.getAmount() - totalPrice;
        double balance = bankService.getBank(1).getTotalBalance();
        bankService.getBank(1).setTotalBalance(balance + totalPrice);
        wallet.setAmount(amount);
        account.setWallet(wallet);
        account.getBasket().getTours().removeAll( account.getBasket().getTours());
        userService.saveAccount(account);
    }

    @Override
    public Double getTotal(Basket basket) {
        return basket.getTours().stream().mapToDouble(Tour::getCost).sum();
    }
}
