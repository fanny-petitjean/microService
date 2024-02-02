package code.boutique.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class controllerLogin {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/proxyLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> proxyLogin(ProxyExchange<byte[]> proxy, @RequestParam MultiValueMap<String, String> formData) throws Exception {
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
        
        return responseEntity;
    }
}