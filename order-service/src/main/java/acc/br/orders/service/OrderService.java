package acc.br.orders.service;

import acc.br.orders.domain.TypeOfStatus;
import acc.br.orders.domain.model.Orders;
import acc.br.orders.dtos.OrderCreatedEvent;
import acc.br.orders.dtos.OrderDTO;
import acc.br.orders.dtos.OrderRequestDTO;
import acc.br.orders.dtos.ProductResponseDTO;
import acc.br.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final RabbitTemplate rabbitTemplate;
    private final ProductServiceClient productService;  // your client REST

    public OrderDTO createOrder(OrderRequestDTO request) {
        // 1️⃣ Persiste a order
        Orders order = new Orders();
        order.setProductId(request.productId());
        order.setQuantity(request.quantity());
        order.setStatus(TypeOfStatus.PROCESSING);
        order.setDateTimeDeparture(request.dateTimeDeparture());
        Orders saved = repository.save(order);

        // 2️⃣ Busca dados do produto
        ProductResponseDTO product = productService.getProductById(saved.getProductId());


        // 3️⃣ Publica evento
        OrderCreatedEvent event = new OrderCreatedEvent(
                saved.getOrderId(),
                product.value(),
                product.productName(),
                product.description(),
                saved.getQuantity(),
                saved.getStatus(),
                saved.getDateTimeDeparture(),
                saved.getCreatedAt(),
                saved.getUpdatedAt(),
                product.sellerId(),
                saved.getProductId()
        );

        System.out.println("Evento enviado -->: " + event.toString());
        rabbitTemplate.convertAndSend("order.v1.order-created.direct", "order.created", event);
        System.out.println("product " + event.productName() + " is " + event.status() + ".....");
        System.out.println(" Waiting.....");


        // 4 retorna dto
        return toDTO(saved, product);
    }

    public OrderDTO getOrderByIdDTO(Long orderId) {
        Orders order = repository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Order with ID " + orderId + " Not Found!"));
        ProductResponseDTO product = productService.getProductById(order.getProductId());
        return toDTO(order, product);
    }

    public List<OrderDTO> getAllOrdersDTO() {
        List<Orders> orders = repository.findAll();
        if (orders.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Orders Found!");
        }
        return orders.stream()
                .map(o -> toDTO(o, productService.getProductById(o.getProductId())))
                .collect(Collectors.toList());
    }

    private OrderDTO toDTO(Orders order, ProductResponseDTO product) {
        return new OrderDTO(
                order.getOrderId(),
                order.getQuantity(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getDateTimeDeparture(),
                product.id(),
                product.productName(),
                product.description(),
                product.value(),
                product.stockQuantity(),
                product.sellerId()
        );
    }
}
