package acc.br.summerAcademy.consumer;

import acc.br.summerAcademy.domain.model.Orders;
import acc.br.summerAcademy.model.Stock;
import acc.br.summerAcademy.repository.StockRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StockConsumer {

    private final RabbitTemplate rabbitTemplate;
    private final StockRepository stockRepository;

    public StockConsumer(RabbitTemplate rabbitTemplate, StockRepository stockRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.stockRepository = stockRepository;
    }

    @RabbitListener(queues = "order.v1.order-created")
    public void processOrder(Orders orders) {
        Optional<Stock> produtoOptional = stockRepository.findByProductNameAndSellerId(
                orders.getProductName(),
                orders.getSeller().getId()
        );

        if (produtoOptional.isPresent()) {
            Stock stock = produtoOptional.get();
            if (stock.getStockQuantity() < orders.getQuantity()) {
                System.out.println("Estoque insuficiente para o produto: " + stock.getProductName());
                // Aqui você pode enviar um status especial ou não enviar nada
                return;
            }

            // Atualiza o estoque
            stock.setStockQuantity(stock.getStockQuantity() - orders.getQuantity());
            stockRepository.save(stock);
            System.out.println("Estoque atualizado para o produto: " + stock.getProductName());

        } else {
            System.out.println("Produto não encontrado: " + orders.getProductName());
            // Aqui você pode enviar uma mensagem de erro também
            return;
        }

        rabbitTemplate.convertAndSend(
                "order.exchange",
                "order.status.updated",        // Routing key para status atualizado
                orders
        );
    }
}
