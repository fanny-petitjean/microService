package code.incubateuroeuf.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import code.incubateuroeuf.model.IncubateurOeuf;
import code.incubateuroeuf.model.IncubateurOeufRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.amqp.core.Queue;



@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerIncubateurOeuf {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    IncubateurOeufRepository incubateurOeufRepository;

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

    @PostMapping("/ajouterOeufIncubateur")
    @ResponseBody
    public Boolean ajouterOeufDansIncubateur(@RequestBody Map<String, Object> formData) throws Exception {
        Integer idOeuf = Integer.parseInt((String )  formData.get("idOeuf"));
        Integer idIncubateur = Integer.parseInt((String )  formData.get("idIncubateur"));
        Integer duree = Integer.parseInt((String )  formData.get("dureeEclosion"));
        String pseudo = (String )  formData.get("pseudo");
        System.out.println(formData);
        // Obtenir la date et l'heure actuelles
        LocalDateTime now = LocalDateTime.now();

        // Ajouter la durée d'éclosion en heures pour obtenir la date d'éclosion
        LocalDateTime dateEclosion = now.plusHours(duree);
        System.out.println("eeeeeeeeeeeeeee "+ dateEclosion );
        IncubateurOeuf incubateurOeuf = new IncubateurOeuf(idIncubateur,pseudo,idOeuf,dateEclosion);
        incubateurOeufRepository.save(incubateurOeuf);
        if(incubateurOeufRepository.existsById(incubateurOeuf.getId())){
            // Envoyer le message après l'ajout de l'oeuf
            String message = "Un oeuf est entrain d'éclore";
            rabbitTemplate.convertAndSend(queue.getName(), message);
            System.out.println(" [x] Sent '" + message + "'");
            rabbitTemplate.convertAndSend(queue.getName(), message);
            return true;
        }else{
            return false;
        }
    }

    @PostMapping("/listerIncubateurOeuf")
    @ResponseBody
    public List<IncubateurOeuf> listerIncubateurOeuf(@RequestParam MultiValueMap<String, String> formData) throws Exception {
        String pseudo = formData.getFirst("pseudo");
        return incubateurOeufRepository.findAllByIdentifiantHero(pseudo);
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
