package acc.br.summerAcademy.dtos;

import acc.br.summerAcademy.domain.TypeOfStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDTO(
        Long orderId,
        String productName,
        BigDecimal value,
        String description,
        Integer quantity,
        TypeOfStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime dateTimeDeparture,
        Long sellerId
) { }
