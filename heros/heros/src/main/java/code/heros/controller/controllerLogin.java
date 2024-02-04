package code.heros.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import code.heros.UserNotFoundException;
import org.springframework.util.MultiValueMap;

import code.heros.model.Hero;
import code.heros.model.HeroRepository;

@RestController
public class controllerLogin {

    @Autowired
    private HeroService heroService;


    @PostMapping("/login")
    public Hero login(@RequestParam MultiValueMap<String, String> formData, HeroRepository heroRepository) {
        Optional<Hero> hero = heroRepository.findByName(formData.getFirst("pseudo"));
        System.out.println(hero);
        if(hero.isEmpty()){
            Hero her1 = new Hero(250000,formData.getFirst("pseudo"));
            return her1;
        }else{
            Hero hero1 = hero.get();
            return hero1;
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