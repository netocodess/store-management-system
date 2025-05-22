package acc.br.orders.dtos;

import acc.br.orders.domain.TypeOfStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderCreatedEvent(
        Long orderId,
        BigDecimal productValue,
        String productName,
        String description,
        Integer quantity,
        TypeOfStatus status,
        LocalDateTime dateTimeDeparture,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long sellerId
) { }
