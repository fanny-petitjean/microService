package code.incubateuroeuf.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void sendHelloMessage() {
        String message = "hello";
        rabbitTemplate.convertAndSend("helloQueue", message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}

