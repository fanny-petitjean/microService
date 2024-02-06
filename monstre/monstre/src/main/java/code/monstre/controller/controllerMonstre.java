package code.monstre.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import code.monstre.model.Monstre;
import code.monstre.model.MonstreRepository;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerMonstre {

    @Autowired
    MonstreRepository monstreRepository;
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

    @PostMapping("/generationMonstres")
    public String genererMonstres() {
        Random random = new Random();
        String[] noms = {
            "Goblin", "Orc", "Dragon", "Squelette", "Troll",
            "Loup-garou", "Vampire", "Banshee", "Sorcier", "Géant",
            "Hydre", "Chimère", "Kraken", "Sphinx", "Cyclope",
            "Liche", "Goule", "Harpie", "Minotaure", "Basilic", "Centaure"
        };
        String[] types = {"Feu", "Eau", "Terre", "Air", "Poison", "Foudre", "Glace", "Sombre", "Lumière", "Fantôme"};
        
        String nom = noms[random.nextInt(noms.length)];
        String type = types[random.nextInt(types.length)];
        int niveau = random.nextInt(100) + 1; 
        int xp = niveau * 10; 
        
        Monstre monstre = new Monstre(nom, type, niveau, xp);
        monstreRepository.save(monstre);
        
        
        return monstre.toString(); 
    }
}