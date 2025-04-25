package acc.br.summerAcademy.service;

import acc.br.summerAcademy.dtos.OrderCreatedEvent;
import acc.br.summerAcademy.domain.model.Orders;
import acc.br.summerAcademy.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepository repository;
    private final RabbitTemplate rabbitTemplate;


    public OrderCreatedEvent createOrder(Orders orders) {
        Orders savedOrders = repository.save(orders);

        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrders.getId_Pedido(),
                savedOrders.getValue(),
                savedOrders.getProductName(),
                savedOrders.getDescription(),
                savedOrders.getQuantity(),
                savedOrders.getStatus(),
                savedOrders.getDateTimeDeparture(),
                savedOrders.getCreatedAt(),
                savedOrders.getUpdatedAt(),
                savedOrders.getSeller().getId()
        );

        System.out.println("Evento enviado -->: " + event.toString());
        rabbitTemplate.convertAndSend("orders.v1.orders-created.direct", "orders.created", event);
        System.out.println( "id " + event.getId_Pedido());
        System.out.println("product " + event.getProductName() + " is " + event.getStatus() + ".....");
        System.out.println("----Waiting.....----");
        return event;

    }
}
