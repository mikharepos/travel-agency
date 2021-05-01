package by.mikhasiuta.controller;

import by.mikhasiuta.bank.BankService;
import by.mikhasiuta.model.Tour;
import by.mikhasiuta.tour.TourService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final TourService tourService;
    private final BankService bankService;

    public AdminController(TourService tourService, BankService bankService) {
        this.tourService = tourService;
        this.bankService = bankService;
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteTour(@PathVariable Integer id) {
        Tour tour = tourService.findById(id);
        tourService.deleteTour(tour);
        return "redirect:/view/tours";
    }

    @GetMapping("/admin/edit/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Tour tour = tourService.findById(id);
        model.addAttribute("tour", tour);
        return "update_tour";
    }

    @PostMapping("/admin/update{id}")
    public String updateTour(@PathVariable Integer id, Tour tour) {
        tourService.updateTour(id, tour);
        return "redirect:/view/tours";
    }

    @GetMapping("/admin/showAddTourForm")
    public String showAddTourForm(Model model) {
        model.addAttribute("tour", new Tour());
        return "add_tour";
    }

    @PostMapping("/admin/addTour")
    public String addTour(Tour tour) {
        tourService.saveTour(tour);
        return "redirect:/view/tours";
    }

    @GetMapping("/admin/bank")
    public String getBank(Model model){
        model.addAttribute("bank", bankService.getBank(1));
        return "bank";
    }
}
