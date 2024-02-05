package code.visuel;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("pseudo")
public class controllerLogin {

    @Autowired
    private RestTemplate restTemplate;

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
        // Rediriger vers la page principale
        return "redirect:/boutique";
    }

}