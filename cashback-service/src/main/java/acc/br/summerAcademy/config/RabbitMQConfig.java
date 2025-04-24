package acc.br.summerAcademy.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("order.exchange");
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public Queue queueOrderCreatedNotification() {
        return new Queue("orders.v1.order-created.send-notification");
    }

    @Bean
    public Binding bindingOrderCreatedNotification(DirectExchange directExchange) {
        return BindingBuilder.bind(queueOrderCreatedNotification())
                .to(directExchange)
                .with("order.created"); // Routing key corrigida
    }
}
