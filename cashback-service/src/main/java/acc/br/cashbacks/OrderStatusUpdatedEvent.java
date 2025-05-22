package acc.br.cashbacks;

import acc.br.orders.domain.TypeOfStatus;

import java.time.LocalDateTime;

public record OrderStatusUpdatedEvent(
        Long orderId,
        TypeOfStatus status,
        LocalDateTime updatedAt
) { }
