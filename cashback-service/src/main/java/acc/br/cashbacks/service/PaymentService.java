package acc.br.cashbacks.service;

import acc.br.orders.domain.model.Orders;
import acc.br.orders.dtos.OrderCreatedEvent;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public void simulatePayment(OrderCreatedEvent event) {
        try {
            System.out.println("Processando pagamento para o pedido ID: " + event.orderId());
            Thread.sleep(4000); // Simula um atraso no pagamento
            System.out.println("Pagamento concluído para o pedido ID: " + event.orderId());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Erro ao simular pagamento para o pedido ID: " + event.orderId());
        }
    }
}
