package code.heros.controller;

import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import code.heros.model.Hero;
import code.heros.model.HeroRepository;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerHero {

    @Autowired
    private HeroRepository heroRepository; // Injection de HeroRepository

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

    @PostMapping("/enleverArgent")
    @ResponseBody
    public String enleverArgent(@RequestBody Map<String, Object> formData){
        String pseudo = (String) formData.get("pseudo");
        String prixString = String.valueOf(formData.get("prix"));
        Integer prix = Integer.parseInt(prixString);
        Optional<Hero> optionalHero = heroRepository.findByName(pseudo);
        if (optionalHero.isPresent()) {
            Hero hero = optionalHero.get();
            Integer argent = hero.getArgent() - prix;
            hero.setArgent(argent);
            heroRepository.save(hero);
            return "ça marche, normalement";
        } else {
            return "Héros non trouvé";
        }
    }

    @PostMapping("/ajouterArgent")
    @ResponseBody
    public String ajouterArgent(@RequestBody Map<String, Object> formData){
        String pseudo = (String) formData.get("pseudo");
        String prixString = String.valueOf(formData.get("prix"));
        Integer prix = Integer.parseInt(prixString);
        Optional<Hero> optionalHero = heroRepository.findByName(pseudo);
        if (optionalHero.isPresent()) {
            Hero hero = optionalHero.get();
            Integer argent = hero.getArgent() + prix;
            hero.setArgent(argent);
            heroRepository.save(hero);
            return "ça marche, normalement";
        } else {
            return "Héros non trouvé";
        }
    }
}