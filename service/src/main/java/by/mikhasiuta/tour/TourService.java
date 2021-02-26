package by.mikhasiuta.tour;

import by.mikhasiuta.model.Tour;

import java.util.List;

public interface TourService {

    List<Tour> getAllTours();

    Tour findById(Integer id);

    void deleteTour(Tour tour);

    void saveTour(Tour tour);

    void updateTour(Integer id, Tour tour);
}
