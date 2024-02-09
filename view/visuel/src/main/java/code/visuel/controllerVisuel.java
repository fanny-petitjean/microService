package code.visuel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.mvc.ProxyExchange;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("pseudo")
public class controllerVisuel {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public controllerVisuel(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    
    @GetMapping("/")
    public String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        if (pseudo != null && !pseudo.isEmpty()) {
            return "redirect:/boutique";
        }else{
            return "login";
        }        
    }

    @GetMapping("/boutique")
    public String boutique(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        if (pseudo != null && !pseudo.isEmpty()) {

            // Client HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
            bodyMap.add("pseudo", pseudo);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);

            ResponseEntity<Map<String, String>> responseEntity = restTemplate.exchange(
                "http://localhost:3002/informationsHero",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Map<String, String>>() {}
            );

            Map<String, String> heroInfo = responseEntity.getBody();
            String argent = "0";
            String nbIncubateur = "0";
            if (heroInfo != null) {
                argent = heroInfo.get("argent");
                nbIncubateur = heroInfo.get("nbIncubateur");
            } else {
                System.out.println("Aucune information trouvée pour le héros.");
            }
                
            // Construire le corps de la requête avec "pseudo", "nbIncubateur" et "nbOeuf"
            MultiValueMap<String, String> bodyI = new LinkedMultiValueMap<>();
            bodyI.add("nbIncubateur", nbIncubateur);
            HttpHeaders headersI = new HttpHeaders();
            headersI.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> requestEntityI = new HttpEntity<>(bodyI, headersI);

            // Envoyer la requête POST pour lister les incubateurs
            ResponseEntity<List<Object>> responseEntityIncubateur = restTemplate.exchange(
                "http://localhost:3001/listerIncubateur",
                HttpMethod.POST,
                requestEntityI,
                new ParameterizedTypeReference<List<Object>>() {}
            );

            List<Object> tabIncubateur = responseEntityIncubateur.getBody();
            Integer id = 20;
            // Récupérer la liste d'objets de la réponse pour les incubateurs
            MultiValueMap<String, String> bodyO = new LinkedMultiValueMap<>();
            bodyO.add("nbOeuf", id.toString());
            HttpHeaders headersO = new HttpHeaders();
            headersO.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> requestEntityO = new HttpEntity<>(bodyO, headersO);
            // Envoyer la requête POST pour lister les œufs
            ResponseEntity<List<Object>> responseEntityOeuf = restTemplate.exchange(
                "http://localhost:3001/listerOeuf",
                HttpMethod.POST,
                requestEntityO,
                new ParameterizedTypeReference<List<Object>>() {}
            );


            // Récupérer la liste d'objets de la réponse
            List<Object> tabOeuf = responseEntityOeuf.getBody();
            model.addAttribute("tabOeuf", tabOeuf); 
            model.addAttribute("tabIncubateur", tabIncubateur); 
            model.addAttribute("pseudo", pseudo);
            model.addAttribute("argent", argent);
            return "boutique";
        } else {
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
    }

    @GetMapping("/inventaire")
    public String inventaire(HttpServletRequest request,Model model, ProxyExchange<byte[]> proxy) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        if (pseudo != null && !pseudo.isEmpty()) {
            HttpHeaders headershero = new HttpHeaders();
            headershero.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String bodyHero = "pseudo=" + pseudo;
            HttpEntity<String> requestEntityHero = new HttpEntity<>(bodyHero, headershero);

            // Envoyer la requête POST avec "pseudo" dans le corps
            ResponseEntity<Object> responseEntityHero = restTemplate.exchange(
                "http://localhost:3002/informationHero",
                HttpMethod.POST,
                requestEntityHero,
                new ParameterizedTypeReference<Object>() {}
            );

            // Récupérer la liste d'objets de la réponse
            Object tabHero = responseEntityHero.getBody();
            // Faites quelque chose avec la liste d'objets, par exemple, les ajouter à un modèle
            model.addAttribute("tabHero", tabHero); 


            // Construire le corps de la requête avec "pseudo"
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String body = "pseudo=" + pseudo;
            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<List<Object>> responseEntity = restTemplate.exchange(
                "http://localhost:3004/listerIncubateur",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Object>>() {}
            );

            // Récupérer la liste d'objets de la réponse
            List<Object> tabIncubateur = responseEntity.getBody();
            // Construire le corps de la requête avec "pseudo"
            HttpHeaders headersOeuf = new HttpHeaders();
            headersOeuf.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String bodyOeuf = "pseudo=" + pseudo;
            HttpEntity<String> requestEntityOeuf = new HttpEntity<>(bodyOeuf, headersOeuf);

            // Envoyer la requête POST avec "pseudo" dans le corps
            ResponseEntity<List<Object>> responseEntityOeuf = restTemplate.exchange(
                "http://localhost:3006/listerOeuf",
                HttpMethod.POST,
                requestEntityOeuf,
                new ParameterizedTypeReference<List<Object>>() {}
            );

            // Récupérer la liste d'objets de la réponse
            List<Object> tabOeuf = responseEntityOeuf.getBody();
            System.out.println(tabOeuf);
            System.out.println(tabIncubateur);
            model.addAttribute("tabOeuf", tabOeuf); 
            model.addAttribute("tabIncubateur", tabIncubateur);        
            model.addAttribute("pseudo", pseudo);
            return "inventaire";
        } else {
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
    }


    @GetMapping("/inventaire_monstre")
    public String inventaire_monstre(HttpServletRequest request,Model model, ProxyExchange<byte[]> proxy) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        System.out.println(pseudo);
        if (pseudo != null && !pseudo.isEmpty()) {
            // Construire le corps de la requête avec "pseudo"
            HttpHeaders headersOeuf = new HttpHeaders();
            headersOeuf.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String bodyOeuf = "pseudo=" + pseudo;
            HttpEntity<String> requestEntityOeuf = new HttpEntity<>(bodyOeuf, headersOeuf);

            // Envoyer la requête POST avec "pseudo" dans le corps
            ResponseEntity<List<Object>> responseEntityOeuf = restTemplate.exchange(
                "http://localhost:3005/listerMonstre",
                HttpMethod.POST,
                requestEntityOeuf,
                new ParameterizedTypeReference<List<Object>>() {}
            );

            // Récupérer la liste d'objets de la réponse
            List<Object> tabMonstre = responseEntityOeuf.getBody();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String body = "pseudo=" + pseudo;
            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

            // Envoyer la requête POST avec "pseudo" dans le corps
            ResponseEntity<Object> responseEntity = restTemplate.exchange(
                "http://localhost:3002/informationHero",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Object>() {}
            );

            // Récupérer la liste d'objets de la réponse
            Object tabHero = responseEntity.getBody();
            System.out.println(tabHero);
            // Faites quelque chose avec la liste d'objets, par exemple, les ajouter à un modèle
            model.addAttribute("tabHero", tabHero); 
            
            // Faites quelque chose avec la liste d'objets, par exemple, les ajouter à un modèle
            model.addAttribute("tabMonstre", tabMonstre); 
            System.out.println(tabMonstre);

            model.addAttribute("pseudo", pseudo);
            return "inventaire_monstre";
        } else {
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
    }
   
    @GetMapping("/incubation")
    public String incubation(HttpServletRequest request,Model model, ProxyExchange<byte[]> proxy) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        if (pseudo != null && !pseudo.isEmpty()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String body = "pseudo=" + pseudo;
            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<List<Object>> responseEntity = restTemplate.exchange(
                "http://localhost:3003/listerIncubateurOeuf",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Object>>() {}
            );

            // Récupérer la liste d'objets de la réponse
            List<Object> tabIncubateur = responseEntity.getBody();
            // Filtrer les incubateurs dont la date d'éclosion est supérieure à maintenant
            List<Object> incubateursAvecDateFuture = tabIncubateur.stream()
                .filter(obj -> {
                    LocalDateTime dateEclosion = getDateEclosionFromObject(obj);
                    return dateEclosion != null && dateEclosion.isBefore(LocalDateTime.now());
                })
                .collect(Collectors.toList());
            
            model.addAttribute("incubateursAvecDateFuture", incubateursAvecDateFuture);        

            model.addAttribute("tabIncubateur", tabIncubateur);        
            model.addAttribute("pseudo", pseudo);
            return "incubateur";
        }else{
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
    }

    public LocalDateTime getDateEclosionFromObject(Object obj){
        LocalDateTime dateTime = null;
        if (obj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) obj;
            Object dateFinEclosionObj = map.get("dateFinEclosion");
            String dateString = dateFinEclosionObj.toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
            dateTime = LocalDateTime.parse(dateString, formatter);

        } else {
            System.out.println("L'objet n'est pas un Map");
        }
        return dateTime;

    }

    @GetMapping("/compte")
    public String compte(HttpServletRequest request,Model model, ProxyExchange<byte[]> proxy) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        System.out.println(pseudo);
        if (pseudo != null && !pseudo.isEmpty()) {
            // Construire le corps de la requête avec "pseudo"
            HttpHeaders headersOeuf = new HttpHeaders();
            headersOeuf.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String bodyOeuf = "pseudo=" + pseudo;
            HttpEntity<String> requestEntityOeuf = new HttpEntity<>(bodyOeuf, headersOeuf);

            // Envoyer la requête POST avec "pseudo" dans le corps
            ResponseEntity<Object> responseEntityOeuf = restTemplate.exchange(
                "http://localhost:3002/informationHero",
                HttpMethod.POST,
                requestEntityOeuf,
                new ParameterizedTypeReference<Object>() {}
            );

            // Récupérer la liste d'objets de la réponse
            Object tabHero = responseEntityOeuf.getBody();
            System.out.println(tabHero);
            // Faites quelque chose avec la liste d'objets, par exemple, les ajouter à un modèle
            model.addAttribute("tabHero", tabHero); 
            model.addAttribute("pseudo", pseudo);
            return "compte";
        } else {
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
    }

    
    @GetMapping("/stockage")
    public String stockage(HttpServletRequest request,Model model, ProxyExchange<byte[]> proxy) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        System.out.println(pseudo);
        if (pseudo != null && !pseudo.isEmpty()) {
            // Construire le corps de la requête avec "pseudo"
            HttpHeaders headersOeuf = new HttpHeaders();
            headersOeuf.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String bodyOeuf = "pseudo=" + pseudo;
            HttpEntity<String> requestEntityOeuf = new HttpEntity<>(bodyOeuf, headersOeuf);

            // Envoyer la requête POST avec "pseudo" dans le corps
            ResponseEntity<List<Object>> responseEntityOeuf = restTemplate.exchange(
                "http://localhost:3008/listerMonstre",
                HttpMethod.POST,
                requestEntityOeuf,
                new ParameterizedTypeReference<List<Object>>() {}
            );

            // Récupérer la liste d'objets de la réponse
            List<Object> tabMonstre = responseEntityOeuf.getBody();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String body = "pseudo=" + pseudo;
            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

            // Envoyer la requête POST avec "pseudo" dans le corps
            ResponseEntity<Object> responseEntity = restTemplate.exchange(
                "http://localhost:3002/informationHero",
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<Object>() {}
            );

            // Récupérer la liste d'objets de la réponse
            Object tabHero = responseEntity.getBody();
            System.out.println(tabHero);
            // Faites quelque chose avec la liste d'objets, par exemple, les ajouter à un modèle
            model.addAttribute("tabHero", tabHero); 
            
            // Faites quelque chose avec la liste d'objets, par exemple, les ajouter à un modèle
            model.addAttribute("tabMonstre", tabMonstre); 
            System.out.println(tabMonstre);

            model.addAttribute("pseudo", pseudo);
            return "stockage";
        } else {
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
    }
/*
    @GetMapping("/alerte")
    public String alerte() {
        // JavaScript pour afficher une boîte de dialogue de confirmation
        return "test";
    }*/
     
}