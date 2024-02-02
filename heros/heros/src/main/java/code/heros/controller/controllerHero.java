package code.heros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerHero {

    @GetMapping("")
    @ResponseBody
    public String helloWorld() {
        return "Hello, World!";
    }

    @GetMapping("/t")
    @ResponseBody
    public String t() {
        System.out.println("cheval");
        return "TA MERE!";
    }

    @PostMapping("/test")
    @ResponseBody
    public String submitData(@RequestBody String data) {
        return "ça marche, normalement";
    }
    // Ajoutez d'autres méthodes du contrôleur selon vos besoins
}