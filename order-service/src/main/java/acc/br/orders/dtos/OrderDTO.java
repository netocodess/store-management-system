package acc.br.orders.dtos;

import acc.br.orders.domain.TypeOfStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDTO(
        Long orderId,
        Integer quantity,
        TypeOfStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        LocalDateTime dateTimeDeparture,

        // Data of product
        Long productId,
        String productName,
        String description,
        BigDecimal value,
        Integer stockQuantity,
        Long sellerId
) { }
