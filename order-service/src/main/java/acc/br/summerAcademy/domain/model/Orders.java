package acc.br.summerAcademy.domain.model;

import acc.br.summerAcademy.domain.TypeOfStatus;
import acc.br.summerAcademy.model.Product;
import acc.br.summerAcademy.model.Seller;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "sellerId", nullable = false)
    private Seller seller;

    @ManyToOne
    @JoinColumn(name = "productId", nullable = false)
    private Product product; // Relacionamento com a entidade Product
    // Referência ao Produto

    @Column(nullable = false)
    private Integer quantity = 1;

    @Enumerated(EnumType.STRING)
    private TypeOfStatus status = TypeOfStatus.PROCESSING;

    private LocalDateTime dateTimeDeparture;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
