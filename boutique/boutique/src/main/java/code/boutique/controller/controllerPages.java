package code.boutique.controller;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import org.springframework.ui.Model;

import code.boutique.model.Incubateur;
import code.boutique.model.IncubateurRepository;
import code.boutique.model.Oeuf;
import code.boutique.model.OeufRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("pseudo")
public class controllerPages {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OeufRepository oeufRepository;
    @Autowired
    private IncubateurRepository incubateurRepository;

    @Autowired
    public controllerPages(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    
    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/boutique")
    public String boutique(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        System.out.println(pseudo);
        if (pseudo != null && !pseudo.isEmpty()) {
            model.addAttribute("pseudo", pseudo);
            List<Oeuf> oeufs = oeufRepository.findAll(); // Récupère tous les oeufs de la base de données
            model.addAttribute("tabOeuf", oeufs);
            List<Incubateur> incubateurs = incubateurRepository.findAll(); // Récupère tous les oeufs de la base de données
            model.addAttribute("tabIncubateur", incubateurs);   
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
        System.out.println(pseudo);
        if (pseudo != null && !pseudo.isEmpty()) {

            // Construire le corps de la requête avec "pseudo"
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String body = "pseudo=" + pseudo;
            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

            // Envoyer la requête POST avec "pseudo" dans le corps
            // Envoyer la requête POST avec "pseudo" dans le corps
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
            // Faites quelque chose avec la liste d'objets, par exemple, les ajouter à un modèle
            model.addAttribute("tabOeuf", tabOeuf); 
            // Faites quelque chose avec la liste d'objets, par exemple, les ajouter à un modèle
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
            // Faites quelque chose avec la liste d'objets, par exemple, les ajouter à un modèle
            model.addAttribute("tabMonstre", tabMonstre); 
            model.addAttribute("pseudo", pseudo);
            return "inventaire_monstre";
        } else {
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
    }
    @GetMapping("/vente")
    public String vente(HttpServletRequest request,Model model, ProxyExchange<byte[]> proxy) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        System.out.println(pseudo);
        if (pseudo != null && !pseudo.isEmpty()) {
            return "vente";
        } else {
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
    }
    @GetMapping("/incubation")
    public String incubation(HttpServletRequest request,Model model, ProxyExchange<byte[]> proxy) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        System.out.println(pseudo);
        if (pseudo != null && !pseudo.isEmpty()) {
            return "incubateur";
        } else {
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
    }
    @GetMapping("/compte")
    public String compte(HttpServletRequest request,Model model, ProxyExchange<byte[]> proxy) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        System.out.println(pseudo);
        if (pseudo != null && !pseudo.isEmpty()) {
            return "compte";
        } else {
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
    }
     
}