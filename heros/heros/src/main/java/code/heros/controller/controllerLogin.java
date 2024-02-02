package code.heros.controller;

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

@RestController
public class controllerLogin {

    @Autowired
    private HeroService heroService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam MultiValueMap<String, String> formData) {
        System.out.println("tezpotepozekzep");
        return null;
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