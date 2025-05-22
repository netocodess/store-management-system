package acc.br.cashbacks.consumer;

import acc.br.cashbacks.service.PaymentService;
import acc.br.orders.domain.TypeOfStatus;
import acc.br.orders.domain.model.Orders;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

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
    public void processOrder(Orders orders) {
        System.out.println("Recebendo pedido para processamento: " + orders);

        paymentService.simulatePayment(orders);

        orders.setStatus(TypeOfStatus.DELIVERED);

        System.out.println("Pedido processado e atualizado: " + orders);
        System.out.println(orders.getStatus() + "!");

        // Enviar o pedido atualizado para a fila de status
        rabbitTemplate.convertAndSend(
                "order.exchange",     // Exchange correta
                "order.created",           // Routing key para status atualizado
                orders
        );
    }
}