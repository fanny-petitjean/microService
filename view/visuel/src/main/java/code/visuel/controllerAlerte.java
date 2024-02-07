package code.visuel;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class controllerAlerte {

    @MessageMapping("/update")
    @SendTo("/topic/updates")
    public String sendUpdate() {
        System.out.println("maj");
        return "La Boutique a été mise à jour";
    }
    
}
