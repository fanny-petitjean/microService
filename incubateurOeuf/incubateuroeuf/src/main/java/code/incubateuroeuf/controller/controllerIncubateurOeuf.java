package code.incubateuroeuf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.gateway.mvc.ProxyExchange;

import java.util.List;


import code.incubateuroeuf.model.IncubateurOeuf;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerIncubateurOeuf {

    @Autowired
    private IncubateurOeuf incubateurOeuf;


    @GetMapping("")
    @ResponseBody
    public String helloWorld() {
        return "Hello, World!";
    }

    @PostMapping("/test")
    @ResponseBody
    public String submitData(@RequestBody String data) {
        return helloWorld() + "ça marche, normalement";
    }

    @Autowired
    private RestTemplate restTemplate;

   /* @PostMapping(value = "/proxyLogin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String proxyLogin(HttpServletRequest request,Model model, ProxyExchange<byte[]> proxy, @RequestParam MultiValueMap<String, String> formData) throws Exception {
        // Récupérer la valeur de "pseudo" du formulaire
        String identifiantHero = formData.getFirst("identifiantHero");
        System.out.println("identifiantHero: " + identifiantHero);

        // Construire le corps de la requête avec "pseudo"
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String body = "identifiantHero=" + identifiantHero;
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // Envoyer la requête POST avec "pseudo" dans le corps
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:3002/login", requestEntity, String.class);
        System.out.println(responseEntity);
        model.addAttribute("identifiantHero", identifiantHero);
        HttpSession session = request.getSession();
        session.setAttribute("identifiantHero", identifiantHero);
        // Rediriger vers la page principale
        return "redirect:/boutique";
    }*/ 
    @PostMapping(value = "/ajouterOeufIncubateur", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
public String ajouterOeufDansIncubateur(HttpServletRequest request, Model model, ProxyExchange<byte[]> proxy, @RequestParam MultiValueMap<String, String> formData) throws Exception {
    // Récupérer les valeurs nécessaires du formulaire
    String identifiantHero = (String) request.getSession().getAttribute("identifiantHero");
    String idOeuf = formData.getFirst("idOeuf");
    String idIncubateur = formData.getFirst("idIncubateur");

    // Construire le corps de la requête pour ajouter l'œuf dans l'incubateur
    HttpHeaders headersAjout = new HttpHeaders();
    headersAjout.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    String bodyAjout = "identifiantHero=" + identifiantHero + "&idOeuf=" + idOeuf + "&idIncubateur=" + idIncubateur;
    HttpEntity<String> requestEntityAjout = new HttpEntity<>(bodyAjout, headersAjout);

    // Envoyer la requête POST pour ajouter l'œuf dans l'incubateur
    ResponseEntity<String> responseEntityAjout = restTemplate.postForEntity("http://localhost:3003/ajouterOeufIncubateur", requestEntityAjout, String.class);
    System.out.println(responseEntityAjout);

    // Construire le corps de la requête pour supprimer l'œuf de l'inventaire
    HttpHeaders headersSuppressionOeuf = new HttpHeaders();
    headersSuppressionOeuf.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    String bodySuppressionOeuf = "identifiantHero=" + identifiantHero + "&idOeuf=" + idOeuf;
    HttpEntity<String> requestEntitySuppressionOeuf = new HttpEntity<>(bodySuppressionOeuf, headersSuppressionOeuf);

    // Envoyer la requête POST pour supprimer l'œuf de l'inventaire
    ResponseEntity<String> responseEntitySuppressionOeuf = restTemplate.postForEntity("http://localhost:3006/enleverOeuf", requestEntitySuppressionOeuf, String.class);
    System.out.println(responseEntitySuppressionOeuf);

    // Construire le corps de la requête pour supprimer l'incubateur de l'inventaire
    HttpHeaders headersSuppressionIncubateur = new HttpHeaders();
    headersSuppressionIncubateur.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    String bodySuppressionIncubateur = "identifiantHero=" + identifiantHero + "&idIncubateur=" + idIncubateur;
    HttpEntity<String> requestEntitySuppressionIncubateur = new HttpEntity<>(bodySuppressionIncubateur, headersSuppressionIncubateur);

    // Envoyer la requête POST pour supprimer l'incubateur de l'inventaire incubateur
    ResponseEntity<String> responseEntitySuppressionIncubateur = restTemplate.postForEntity("http://localhost:3004/enleverIncubateur", requestEntitySuppressionIncubateur, String.class);
    System.out.println(responseEntitySuppressionIncubateur);

    // Rediriger vers la page principale
    return "redirect:/inventaire";
}


    // ajouter des oeufs dans les incubateurs
   /*  @PostMapping("/ajouterOeuf")
    public String ajouterOeuf() {
        String value = "test";
        //Vérifier le nombre d'incubateur de libre
         List<IncubateurOeuf> incubateursOeufs = incubateurOeuf.getIncubateursOeufsByHero(identifiantHero);
       
        }
    


    // Éclosion d'œufs
   /* @PostMapping("/ecloreOeuf")
    @ResponseBody
    public String ecloreOeuf(@RequestParam Integer idIncubateurOeuf) {
        // Implémenter la logique pour l'éclosion d'œufs
        incubateurOeuf.ecloreOeuf(idIncubateurOeuf);
        return "Opération d'éclosion effectuée avec succès.";*/ 
    }
