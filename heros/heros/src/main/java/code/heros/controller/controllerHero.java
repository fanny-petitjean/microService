package code.heros.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/enleverArgent")
    @ResponseBody
    public Boolean enleverArgent(@RequestBody Map<String, Object> formData){
        String pseudo = (String) formData.get("pseudo");
        String prixString = String.valueOf(formData.get("prix"));
        Integer prix = Integer.parseInt(prixString);
        String produit= (String) formData.get("produit");
        Optional<Hero> optionalHero = heroRepository.findByName(pseudo);
        if (optionalHero.isPresent()) {
            Hero hero = optionalHero.get();
            Integer argent = hero.getArgent() - prix;
            if(argent < 0){
                return false;
            }else {
                hero.setArgent(argent);
                if(produit.equals("incubateur")){
                    System.out.println("kekekekekkek");
                    Integer nb = hero.getNbIncubateur() + 1;
                    hero.setNbIncubateur(nb);
                }
                heroRepository.save(hero);

                return true;
            }
        } else {
            return false;
        }
    }

    @PostMapping("/ajouterArgent")
    @ResponseBody
    public Boolean ajouterArgent(@RequestBody Map<String, Object> formData){
        String pseudo = (String) formData.get("pseudo");
        String prixString = String.valueOf(formData.get("prix"));
        Integer prix = Integer.parseInt(prixString);
        String produit= (String) formData.get("produit");
        Optional<Hero> optionalHero = heroRepository.findByName(pseudo);
        if (optionalHero.isPresent()) {
            Hero hero = optionalHero.get();
            Integer argent = hero.getArgent() + prix;
            hero.setArgent(argent);
            if(produit == "incubateur"){
                Integer nb = hero.getNbIncubateur() - 1;
                hero.setNbIncubateur(nb);
            }
            heroRepository.save(hero);

            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/informationHero")
    @ResponseBody
    public ResponseEntity<Hero> informationHero(@RequestParam("pseudo") String pseudo) {
        Optional<Hero> optionalHero = heroRepository.findByName(pseudo);
        return optionalHero.map(hero -> ResponseEntity.ok().body(hero))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/informationsHero")
    @ResponseBody
    public Map<String, String> informationsHero(@RequestParam MultiValueMap<String, String> formData) {
        String pseudo = formData.getFirst("pseudo");
        Optional<Hero> optionalHero = heroRepository.findByName(pseudo);
        Map<String, String> heroInfo = new HashMap<>();
        if (optionalHero.isPresent()) {
            Hero hero = optionalHero.get();
            // Ajouter les informations de l'hero au Map
            heroInfo.put("id", String.valueOf(hero.getId()));
            heroInfo.put("pseudo", hero.getName());
            heroInfo.put("argent", hero.getArgent().toString());
            heroInfo.put("nbIncubateur", hero.getNbIncubateur().toString());
            // Ajouter d'autres informations si nécessaire
        }
        return heroInfo;
    }


    @PostMapping("/inbIncubateurHero")
    @ResponseBody
    public Integer inbIncubateurHero(@RequestParam("pseudo") String pseudo) {
        Optional<Hero> optionalHero = heroRepository.findByName(pseudo);
        Hero h = optionalHero.get();
        return h.getNbIncubateur(); 
    }

    public Boolean enleverIncubateur(@RequestBody Map<String, Object> formData){
        String pseudo = (String) formData.get("pseudo");
        Optional<Hero> optionalHero = heroRepository.findByName(pseudo);
        if (optionalHero.isPresent()) {
            Hero hero = optionalHero.get();
            hero.setNbIncubateur(hero.getNbIncubateur()-1);
            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/ajouterIncubateur")
    @ResponseBody
    public Boolean ajouterIncubateur(@RequestBody Map<String, Object> formData){
        String pseudo = (String) formData.get("pseudo");
        Optional<Hero> optionalHero = heroRepository.findByName(pseudo);
        if (optionalHero.isPresent()) {
            Hero hero = optionalHero.get();
            hero.setNbIncubateur(hero.getNbIncubateur()+1);
            return true;
        } else {
            return false;
        }
    }

    
}