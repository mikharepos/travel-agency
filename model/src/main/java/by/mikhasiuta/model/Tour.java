package by.mikhasiuta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tours")
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String destination;

    private String departure;

    private double cost;

    private Date startTime;

    private Date endTime;

    private String tourName;

    @Column(columnDefinition="varchar(1000)")
    private String description;

    @Enumerated(EnumType.STRING)
    private StatusOfTheTour statusOfTheTour;

    @Enumerated(EnumType.STRING)
    private TypeOfTour typeOfTour;

    @ManyToMany(mappedBy = "tours", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Basket> baskets;
}
