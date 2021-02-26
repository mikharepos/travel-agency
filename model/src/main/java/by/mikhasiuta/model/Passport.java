package by.mikhasiuta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.sql.Date;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passports")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long passportId;

    private String firstName;

    private String lastName;

    private String passportSeries;

    private String passportNumber;

    private Date birthDate;

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "pasports_visas",
            joinColumns = @JoinColumn(name = "passport_id"),
            inverseJoinColumns = @JoinColumn(name = "visa_id"))
    private List<Visa> visas = new ArrayList<>();

    @OneToOne(mappedBy = "passport")
    private Account account;
}
