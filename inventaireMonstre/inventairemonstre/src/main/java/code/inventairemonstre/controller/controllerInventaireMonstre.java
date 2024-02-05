package code.inventairemonstre.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import code.inventairemonstre.model.InventaireMonstre;
import code.inventairemonstre.model.InventaireMonstreRepository;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerInventaireMonstre {

    @Autowired
    InventaireMonstreRepository inventaireMonstreRepository;

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
    // afficher les infos Monstre
    @PostMapping("/afficherMonstre")
    public String afficherMonstre() {
        String value = "test";
        //Demander les infos dans la BD
        // renvoyer les infos
        return value;
    }
    // afficher les infos Monstre
    @PostMapping("/listerMonstre")
    @ResponseBody
    public List<InventaireMonstre> listerMonstre(@RequestParam MultiValueMap<String, String> formData) {
        String pseudo = formData.getFirst("pseudo");
        List<InventaireMonstre> i = inventaireMonstreRepository.findByIdentifiantHero(pseudo);
        return i;
    }
}