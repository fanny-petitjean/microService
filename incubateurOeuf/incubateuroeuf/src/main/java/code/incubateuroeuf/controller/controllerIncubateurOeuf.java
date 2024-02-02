package code.incubateuroeuf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerIncubateurOeuf {

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

    // ajouter des oeufs dans les incubateurs
    @PostMapping("/ajouterOeuf")
    public String ajouterOeuf() {
        String value = "test";
        //Vérifier le nombre d'incubateur de libre
        //ajouter dans la bd 
        // renvoyer si c'est ok
        return value;
    } 

    // éclosion oeuf
    @PostMapping("/ecloreOeuf")
    public String ecloreOeuf() {
        String value = "test";
        //Vérifier le temps des oeufs
        //ajouter / supprimer dans la bd 
        // renvoyer si c'est ok
        return value;
    }
}