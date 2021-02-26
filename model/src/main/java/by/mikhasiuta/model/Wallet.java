package by.mikhasiuta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long walletId;

    private double amount = 0.00;

    @OneToOne(mappedBy = "wallet")
    private Account account;
}
