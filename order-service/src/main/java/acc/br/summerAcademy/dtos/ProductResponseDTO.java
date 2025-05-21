package acc.br.summerAcademy.dtos;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String productName,
        String description,
        BigDecimal value,
        Integer stockQuantity,
        Long sellerId
) { }
