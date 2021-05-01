package by.mikhasiuta.controller;

import by.mikhasiuta.basket.BasketService;
import by.mikhasiuta.model.Passport;
import by.mikhasiuta.model.Visa;
import by.mikhasiuta.passport.PassportService;
import by.mikhasiuta.user.UserService;
import by.mikhasiuta.visa.VisaService;
import by.mikhasiuta.wallet.WalletService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final WalletService walletService;
    private final PassportService passportService;
    private final VisaService visaService;
    private final BasketService basketService;

    public UserController(UserService userService, WalletService walletService, PassportService passportService,
                          VisaService visaService, BasketService basketService) {
        this.userService = userService;
        this.walletService = walletService;
        this.passportService = passportService;
        this.visaService = visaService;
        this.basketService = basketService;
    }

    @GetMapping("/user/profile")
    public String getProfile(Model model) {
        model.addAttribute("account", userService.getCurrentUser());
        model.addAttribute("visas", visaService.findAllVisa());
        model.addAttribute("passport", passportService.getPassportCurrentAccount());
        model.addAttribute("wallet", walletService.getWalletCurrentUser().getAmount());
        if(passportService.getPassportCurrentAccount() != null){
            model.addAttribute("visasInPassport", userService.getCurrentUser().getPassport().getVisas());
        }
            return "profile";
    }

    @PostMapping("/addPassport")
    public String savePassport(Passport passport, Date date) {
        passportService.passportRegister(passport, date);
        return "redirect:/user/profile";
    }

    @PostMapping("/addMoney/{id}")
    public String addMoney(@PathVariable("id") Integer id, String money) {
        walletService.addMoney(money);
        return "redirect:/user/profile";
    }

    @PostMapping("/addVisas")
    public String addVisas(@RequestParam(name = "id") List<Integer> visasId) {
        if(visasId != null) {
            visasId.forEach(id -> {
                Visa visa = visaService.findById(id);
                if (visa != null) {
                    visaService.addVisa(visa);
                }
            });
        }
        return "redirect:/user/profile";
    }

    @GetMapping("/delete/{id}")
    public String deleteVisa(@PathVariable Integer id){
        Visa visa = visaService.findById(id);
        userService.getCurrentUser().getPassport().getVisas().remove(visa);
        userService.saveAccount(userService.getCurrentUser());
       return "redirect:/user/profile";
    }

    @GetMapping("/edit")
    public String showUpdateForm(Model model) {
        Passport passport = userService.getCurrentUser().getPassport();
        model.addAttribute("passport", passport);
        return "update_passport";
    }

    @PostMapping("/account/update")
    public String updatePassport(Passport passport, Model model) {
        passportService.passportUpdate(passport);
        model.addAttribute("visasInPassport", userService.getCurrentUser().getPassport().getVisas());
        return "redirect:/user/profile";
    }

    @GetMapping("add/in/basket/{id}")
    public String addInShoppingCart(@PathVariable("id") Integer id) {
        basketService.addTourInBasket(id);
        return "redirect:/view/tours";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteTourInBasket(@PathVariable("id") Integer id) {
        basketService.deleteTourInBasket(id);
        return "redirect:/user/view/basket";
    }

        @GetMapping("/user/view/basket")
        public String viewShoppingCart(Model model){
            model.addAttribute("tourInCart", userService.getCurrentUser().getBasket().getTours());
            model.addAttribute("total", basketService.getTotal(userService.getCurrentUser().getBasket()));
        return "basket";
        }

        @GetMapping("/user/tours/pay/{total}")
        public String payTours(Model model, @PathVariable String total){
        basketService.paymentService();
        model.addAttribute("thanks", "Спасибо за покупку!");
        model.addAttribute("total", total);
        return "thanks_page";
        }
    }


