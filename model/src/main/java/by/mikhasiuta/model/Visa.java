package by.mikhasiuta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visas")
public class Visa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    private String country;

    private String cost;

    @ManyToMany(mappedBy = "visas", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Passport> passports;

}
