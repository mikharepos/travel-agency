package by.mikhasiuta.controller;

import by.mikhasiuta.tour.TourService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    private final TourService tourService;

    public MainController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/about")
    public String getAbout () {
        return "about";
    }

    @GetMapping("/view/tours")
    public String viewAllTours(Model model){
        model.addAttribute("allTours", tourService.getAllTours());
        return "all_tours";
    }

    @GetMapping("/tour/description/{id}")
    public String viewTourDescription(@PathVariable("id") Integer id, Model model){
        model.addAttribute("tour",tourService.findById(id));
        return "description";
    }
}
