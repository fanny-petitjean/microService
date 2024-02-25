package code.incubateuroeuf.config;



import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import code.incubateuroeuf.queue.Sender;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Queue hello() {
        return new Queue("helloQueue");
    }
     @Bean
    public Sender sender() {
        return new Sender();
    }
}
