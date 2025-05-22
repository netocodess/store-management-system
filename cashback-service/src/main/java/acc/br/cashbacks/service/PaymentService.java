package acc.br.cashbacks.service;

import acc.br.orders.domain.model.Orders;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public void simulatePayment(Orders orders) {
        try {
            System.out.println("Processando pagamento para o pedido ID: " + orders.getId_Pedido());
            Thread.sleep(4000); // Simula um atraso no pagamento
            System.out.println("Pagamento concluído para o pedido ID: " + orders.getId_Pedido());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Erro ao simular pagamento para o pedido ID: " + orders.getId_Pedido());
        }
    }
}
