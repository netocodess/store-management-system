package acc.br.summerAcademy.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "store")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerId;

    @Column(name = "storeName", nullable = false)
    private String storeName;
}
