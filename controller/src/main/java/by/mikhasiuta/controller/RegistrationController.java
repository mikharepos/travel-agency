package by.mikhasiuta.controller;

import by.mikhasiuta.model.Account;
import by.mikhasiuta.registration.RegistrationService;
import by.mikhasiuta.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserRepository userRepository;

    @Autowired
    public RegistrationController(RegistrationService registrationService, UserRepository userRepository) {
        this.registrationService = registrationService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String addAccount(Account account, Model model) {
        Account accountFromDb = userRepository.findByLogin(account.getLogin());
        if (accountFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "register";
        }

        registrationService.registerUser(account);
        return "redirect:/login";
    }
}
