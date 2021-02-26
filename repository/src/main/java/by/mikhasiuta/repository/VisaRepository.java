package by.mikhasiuta.repository;

import by.mikhasiuta.model.Visa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaRepository extends JpaRepository<Visa, Integer> {

}
