package by.mikhasiuta.tour;

import by.mikhasiuta.model.Tour;
import by.mikhasiuta.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    @Override
    public Tour findById(Integer id) {
        return tourRepository.getOne(id);
    }

    @Override
    public void deleteTour(Tour tour) {
        tourRepository.delete(tour);
    }

    @Override
    public void saveTour(Tour tour) {
    tourRepository.save(tour);
    }

    @Override
    public void updateTour(Integer id, Tour tour) {
            Tour tour1 = findById(id);
            tour1.setTourName(tour.getTourName());
            tour1.setDeparture(tour.getDeparture());
            tour1.setDestination(tour.getDestination());
            tour1.setCost(tour.getCost());
            tour1.setStartTime(tour.getStartTime());
            tour1.setEndTime(tour.getEndTime());
            tour1.setStatusOfTheTour(tour.getStatusOfTheTour());
            tour1.setTypeOfTour(tour.getTypeOfTour());
            tour1.setDescription(tour.getDescription());
            tourRepository.save(tour1);
    }
}

