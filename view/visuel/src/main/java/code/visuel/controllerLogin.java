package code.visuel;
import org.springframework.http.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("pseudo")
public class controllerLogin {
    @Autowired
    private controllerAlerte controllerAlerte;
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HttpServletRequest request;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    

    @PostMapping(value = "/proxyLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String proxyLogin(HttpServletRequest request,Model model, ProxyExchange<byte[]> proxy, @RequestParam MultiValueMap<String, String> formData) throws Exception {
        // Récupérer la valeur de "pseudo" du formulaire
        String pseudo = formData.getFirst("pseudo");
        System.out.println("Pseudo: " + pseudo);

        // Construire le corps de la requête avec "pseudo"
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = "pseudo=" + pseudo;
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // Envoyer la requête POST avec "pseudo" dans le corps
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:3002/login", requestEntity, String.class);
        System.out.println(responseEntity);
        model.addAttribute("pseudo", pseudo);
        HttpSession session = request.getSession();
        session.setAttribute("pseudo", pseudo);
        return "redirect:/boutique";
    }


    @Scheduled(fixedRate = 1000*60*15)
    @GetMapping("/miseAJourBoutique")
    public void mettreAJourBoutique2() {

        HttpHeaders headersBoutique = new HttpHeaders();
        headersBoutique.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntityBoutique = new HttpEntity<>(headersBoutique);

        ResponseEntity<Object> responseEntityBoutique = restTemplate.exchange(
            "http://localhost:3001/miseAJourBoutique",
            HttpMethod.GET,
            requestEntityBoutique,
            Object.class
        );
        if (responseEntityBoutique.getStatusCode().is2xxSuccessful()) {
            messagingTemplate.convertAndSend("/topic/updates", "Boutique mise à jour");
        } else {
            System.err.println("Échec de l'appel à miseAJourBoutique. Code de statut : " + responseEntityBoutique.getStatusCodeValue());
        }
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, SessionStatus status) {
        HttpSession session = request.getSession(); 
        if (session != null) {

            session.invalidate(); // Invalide la session
        }
        status.setComplete(); // Termine la session
        // Redirection vers la page de connexion ou une autre page
        return "redirect:/";
    }
    
}