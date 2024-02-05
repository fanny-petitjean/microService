package code.heros.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.MultiValueMap;

import code.heros.model.Hero;
import code.heros.model.HeroRepository;

@RestController
public class controllerLogin {

    @Autowired
    private HeroService heroService;

    @Autowired
    private HeroRepository heroRepository; // Injection de HeroRepository


    @PostMapping("/login")
    public Hero login(@RequestParam MultiValueMap<String, String> formData) {
        Optional<Hero> hero = heroRepository.findByName(formData.getFirst("pseudo"));
        System.out.println(hero);
        if(hero.isEmpty()){
            Hero her1 = new Hero(250000,formData.getFirst("pseudo"));
            System.out.println(her1);
            Hero savedHero = heroRepository.save(her1); // Sauvegarder le nouvel utilisateur dans la base de données

            return savedHero;
        }else{
            Hero hero1 = hero.get();
            System.out.println(hero1);
            Hero savedHero = heroRepository.save(hero1); // Sauvegarder le nouvel utilisateur dans la base de données

            return savedHero;
        }
        //vérifier dans la db l existance du perso
        // si existe pas => création
        //sinon connexion
        /*String pseudo = formData.getFirst("pseudo");
        try {
            heroService.verifyUser(pseudo);
            System.out.println("testffdd");
            return ResponseEntity.ok().body("Bienvenue " + pseudo);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Utilisateur non trouvé");
        }*/
    }

}