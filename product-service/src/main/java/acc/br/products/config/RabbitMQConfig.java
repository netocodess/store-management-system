package acc.br.products.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("order.exchange");
    }
    // Declara a fila para cashback
    @Bean
    public Queue queueOrderStatusUpdated() {
        return new Queue("order.v1.order-status.updated");
    }

    @Bean
    public Binding bindingOrderStatusUpdated(DirectExchange directExchange) {
        return BindingBuilder.bind(queueOrderStatusUpdated())
                .to(directExchange)
                .with("order.status.updated");
    }
}