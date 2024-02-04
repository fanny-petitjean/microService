package code.boutique.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class controllerBoutique {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public controllerBoutique(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // acheter un oeuf
    @GetMapping("/achatOeuf")
    public String acheterOeuf() {
        String value = "test";
        //Envoyer à Inventaire Oeuf par requete POST :
        // identifiant oeuf + pseudo
        return value;
    }   

    // acheter un incubateur
    @PostMapping("/achatIncubateur")
    public String acheterIncubateur() {
        String value = "test";
        //Envoyer à Inventaire Incubateur par requete POST :
        // identifiant Incubateur + pseudo
        // Il vérifiera de son coté si c'est possible ou pas
        // si nb incu>6
        return value;
    }  
    // vendre un oeuf
    @PostMapping("/venteOeuf")
    public String vendreOeuf() {
        String value = "test";
        //Envoyer à Inventaire Oeuf par requete POST :
        // identifiant oeuf + pseudo
        return value;
    }   

    // vendre un incubateur
    @PostMapping("/venteIncubateur")
    public String vendreIncubateur() {
        String value = "test";
        //Envoyer à Inventaire Incubateur par requete POST :
        // identifiant Incubateur + pseudo
        return value;
    }  
}