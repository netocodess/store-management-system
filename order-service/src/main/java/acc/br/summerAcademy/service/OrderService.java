package acc.br.summerAcademy.service;

import acc.br.summerAcademy.dtos.OrderCreatedEvent;
import acc.br.summerAcademy.domain.model.Order;
import acc.br.summerAcademy.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository repository;
    private final RabbitTemplate rabbitTemplate;


    public OrderCreatedEvent createOrder(Order order) {
        Order savedOrder = repository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId_Pedido(),
                savedOrder.getValue(),
                savedOrder.getProductName(),
                savedOrder.getDescription(),
                savedOrder.getQuantity(),
                savedOrder.getStatus(),
                savedOrder.getDateTimeDeparture(),
                savedOrder.getCreatedAt(),
                savedOrder.getUpdatedAt(),
                savedOrder.getSeller().getId()
        );

        System.out.println("Evento enviado -->: " + event.toString());
        rabbitTemplate.convertAndSend("orders.v1.order-created.direct", "order.created", event);
        System.out.println( "id " + event.getId_Pedido());
        System.out.println("product " + event.getProductName() + " is " + event.getStatus() + ".....");
        System.out.println("----Waiting.....----");
        return event;

    }
}
