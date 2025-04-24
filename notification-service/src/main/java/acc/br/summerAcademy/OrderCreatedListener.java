package acc.br.summerAcademy;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    @RabbitListener(queues = "orders.v1.order-created.send-notification")
    public void onOrderCreated(OrderCreatedEvent event) {
        System.out.println("Compra Concluída!\n" +
                "Obrigado por realizar sua compra! \uD83C\uDF89\n" +
                "Seu pedido foi confirmado e já está a caminho. \uD83D\uDE9A\n" +
                "\n" +
                "Acompanhe seu produto com o código de rastreamento:BR3474408699");
    }
}
