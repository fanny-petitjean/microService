package code.boutique.queue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Random;

@Component
public class Receiver {

    @RabbitListener(queues = "helloQueue")
    public void receiveMessage(String message) {
        System.out.println(" [x] Received '" + message + "'");
    }
}