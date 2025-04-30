package acc.br.summerAcademy.consumer;

import acc.br.summerAcademy.domain.model.Orders;
import acc.br.summerAcademy.model.Product;
import acc.br.summerAcademy.repository.ProductRepository;
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
    public void processOrder(Orders orders) {
        Optional<Product> optionalProduct = productRepository.findById(orders.getProduct().getProductId());

        if (optionalProduct.isEmpty()) {
            System.out.println("Produto não encontrado com ID: " + orders.getProduct().getProductId());
            return;
        }

        Product product = optionalProduct.get();
        if (product.getStockQuantity() < orders.getQuantity()) {
            System.out.println("Estoque insuficiente para o produto: " + product.getProductName());
            return;
        }

        product.setStockQuantity(product.getStockQuantity() - orders.getQuantity());
        productRepository.save(product);
        System.out.println("Estoque atualizado para o produto: " + product.getProductName());

        rabbitTemplate.convertAndSend(
                "order.exchange",
                "order.status.updated",        // Routing key para status atualizado
                orders
        );
    }
}
