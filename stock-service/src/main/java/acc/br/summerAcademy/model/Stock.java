package acc.br.summerAcademy.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stocks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_Name")
    private String productName;

    @Column(nullable = false)
    private Long stockQuantity;

    @ManyToOne
    @JoinColumn(name = "sellerId", nullable = false)
    private Seller seller;
}
