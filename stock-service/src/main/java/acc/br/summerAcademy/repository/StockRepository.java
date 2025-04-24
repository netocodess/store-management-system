package acc.br.summerAcademy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import acc.br.summerAcademy.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByProductName(String productName);
}
