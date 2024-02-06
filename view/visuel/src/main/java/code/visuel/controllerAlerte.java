package code.visuel;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class controllerAlerte {

    @MessageMapping("/update")
    @SendTo("/topic/updates")
    public String sendUpdate() {
        return "Boutique mise à jour";
    }
    
}
