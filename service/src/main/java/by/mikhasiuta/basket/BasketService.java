package by.mikhasiuta.basket;

import by.mikhasiuta.model.Basket;

public interface BasketService {

    void basketSave(Basket cart);

    void addTourInBasket(Integer id);

    Double getTotal(Basket basket);

    void deleteTourInBasket(Integer id);

    void paymentService();


}
