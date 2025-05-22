package acc.br.orders.dtos;

import java.time.LocalDateTime;

public record OrderRequestDTO(
        Long productId,
        Integer quantity,
        LocalDateTime dateTimeDeparture
) {}
