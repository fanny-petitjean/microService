package code.incubateuroeuf.controller;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import code.incubateuroeuf.queue.Sender;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private Sender sender;

    @GetMapping("/sendHello")
    public String sendHelloMessage() {
        sender.sendHelloMessage();
        return "Je test pour voir !";
    }
}