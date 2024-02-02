package code.monstre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerMonstre {

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

    // générer des monstres
    @PostMapping("/generationMonstres")
    public String genererMonstres() {
        String value = "test";
        //Générer des monstres aleatoirement
        //envoyer la liste des monstres à la boutique
        return value;
    } 
}