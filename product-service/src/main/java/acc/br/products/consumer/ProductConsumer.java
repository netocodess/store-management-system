package acc.br.products.consumer;

import acc.br.orders.domain.model.Orders;
import acc.br.orders.dtos.OrderCreatedEvent;
import acc.br.products.model.Product;
import acc.br.products.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductConsumer {

    private final RabbitTemplate rabbitTemplate;
    private final ProductRepository productRepository;

    public ProductConsumer(RabbitTemplate rabbitTemplate, ProductRepository productRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.productRepository = productRepository;
    }

    @RabbitListener(queues = "order.v1.order-created")
    public void processOrder(OrderCreatedEvent event) {
        Long productId = event.productId();
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isEmpty()) {
            System.out.println("Produto não encontrado com ID: " + productId);
            return;
        }

        Product product = optionalProduct.get();
        if (product.getStockQuantity() < event.quantity()) {
            System.out.println("Estoque insuficiente para o produto: " + product.getProductName());
            return;
        }

        product.setStockQuantity(product.getStockQuantity() - event.quantity());
        productRepository.save(product);
        System.out.println("Estoque atualizado para o produto: " + product.getProductName());

        rabbitTemplate.convertAndSend(
                "order.exchange",
                "order.status.updated",        // Routing key para status atualizado
                event
        );
    }
}
