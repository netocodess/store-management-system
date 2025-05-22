package acc.br.cashbacks.consumer;

import acc.br.cashbacks.OrderStatusUpdatedEvent;
import acc.br.cashbacks.service.PaymentService;
import acc.br.orders.domain.TypeOfStatus;
import acc.br.orders.domain.model.Orders;
import acc.br.orders.dtos.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentProcessor {

    private final RabbitTemplate rabbitTemplate;
    private final PaymentService paymentService; // Injete via Spring

    public PaymentProcessor(RabbitTemplate rabbitTemplate, PaymentService paymentService) {
        this.rabbitTemplate = rabbitTemplate;
        this.paymentService = paymentService;
    }

    /**
     * Consumir mensagens da fila de pedidos e simular pagamento.
     */
    @RabbitListener(queues = "order.v1.order-status.updated")
    public void processOrder(OrderCreatedEvent event) {
        System.out.println("Recebendo pedido para processamento: " + event);

        OrderStatusUpdatedEvent updatedEvent = new OrderStatusUpdatedEvent(
                event.orderId(),
                TypeOfStatus.DELIVERED,
                LocalDateTime.now()
        );

        paymentService.simulatePayment(event);

        System.out.println("Pedido processado e atualizado: " + TypeOfStatus.DELIVERED);
        System.out.println(TypeOfStatus.DELIVERED + "!");


        // Enviar o pedido atualizado para a fila de status
        rabbitTemplate.convertAndSend(
                "order.exchange",
                "order.created",
                updatedEvent
        );
    }
}