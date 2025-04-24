package acc.br.summerAcademy.consumer;

import acc.br.summerAcademy.model.Stock;
import acc.br.summerAcademy.repository.StockRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import acc.br.summerAcademy.domain.model.Order;

import java.util.Optional;

@Component
public class StockConsumer {

    private final RabbitTemplate rabbitTemplate;
    private final StockRepository stockRepository;

    public StockConsumer(RabbitTemplate rabbitTemplate, StockRepository stockRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.stockRepository = stockRepository;
    }

    @RabbitListener(queues = "orders.v1.order-created")
    public void processOrder(Order order) {
        // Busca o produto pelo nome
        Optional<Stock> produtoOptional = stockRepository.findByProductName(order.getProductName());
        if (produtoOptional.isPresent()) {
            Stock stock = produtoOptional.get();
            if (stock.getStockQuantity() < order.getQuantity()) {
                System.out.println("insufficient stock");
            } else {
                stock.setStockQuantity(stock.getStockQuantity() - order.getQuantity());
                stockRepository.save(stock);
            }
        } else {
            System.out.println("Product not found: ");
        }

        rabbitTemplate.convertAndSend(
                "order.exchange", // Exchange existente
                "order.status.updated",          // Routing key para status atualizado
                order
        );
    }
}
