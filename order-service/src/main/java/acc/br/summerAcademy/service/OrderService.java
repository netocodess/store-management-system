package acc.br.summerAcademy.service;

import acc.br.summerAcademy.domain.model.Orders;
import acc.br.summerAcademy.dtos.OrderCreatedEvent;
import acc.br.summerAcademy.dtos.OrderDTO;
import acc.br.summerAcademy.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final RabbitTemplate rabbitTemplate;


    public OrderDTO createOrder(Orders orders) {
        Orders savedOrder = repository.save(orders);

        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getOrderId(),
                savedOrder.getProduct().getValue(),
                savedOrder.getProduct().getProductName(),
                savedOrder.getProduct().getDescription(),
                savedOrder.getQuantity(),
                savedOrder.getStatus(),
                savedOrder.getDateTimeDeparture(),
                savedOrder.getCreatedAt(),
                savedOrder.getUpdatedAt(),
                savedOrder.getSeller().getId()
        );

        System.out.println("Evento enviado -->: " + event.toString());
        rabbitTemplate.convertAndSend("order.v1.order-created.direct", "order.created", event);
        System.out.println("id " + event.orderId());
        System.out.println("product " + event.productName() + " is " + event.status() + ".....");
        System.out.println("----Waiting.....----");

        return convertToDTO(savedOrder);

    }


    public Optional<Orders> getOrderById(Long orderId) {
        Optional<Orders> order = repository.findById(orderId);


        if (!order.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Order with ID " + orderId + " Not Found!"
            );
        }

        return order;
    }


    public List<Orders> getAllOrders() {
        List<Orders> orders = repository.findAll();

        // Verifica se há pedidos, caso contrário lança um erro com mensagem personalizada
        if (orders.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No Orders Found!"
            );
        }

        return orders;
    }

    private OrderDTO convertToDTO(Orders order) {
        return new OrderDTO(
                order.getOrderId(),
                order.getProduct().getProductName(),
                order.getProduct().getValue(),
                order.getProduct().getDescription(),
                order.getQuantity(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt(),
                order.getDateTimeDeparture(),
                order.getSeller().getId()
        );
    }
}
