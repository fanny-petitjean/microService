package code.boutique.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import code.boutique.model.Incubateur;
import code.boutique.model.IncubateurRepository;
import code.boutique.model.Oeuf;
import code.boutique.model.OeufRepository;

@Controller
public class controllerBoutique {


    @Autowired
    private OeufRepository oeufRepository;

    @Autowired
    private IncubateurRepository incubateurRepository;

    @PostMapping("/listerOeuf")
    @ResponseBody
    public  List<Oeuf>  acheterOeuf() {
        List<Oeuf> oeufs = oeufRepository.findAll(); 
        return oeufs;
    }   

    @PostMapping("/listerIncubateur")
    @ResponseBody
    public List<Incubateur> acheterIncubateur() {
        List<Incubateur> incubateurs = incubateurRepository.findAll();
        return incubateurs;
    }  

    public  Boolean genererOeuf() {
        Random random = new Random();

        // Génération du prix entre 100 et 500 (sans décimale)
        int prix = random.nextInt(401) + 100;  // Entre 0 et 400 + 100

        // Génération du temps d'éclosion en heures
        int tempsEclosion = random.nextInt(24); // Entre 0 et 23

        Oeuf oeuf = new Oeuf();
        oeuf.setPrix(prix);
        oeuf.setDureeEclosion(tempsEclosion);

        // Enregistrer l'œuf dans la base de données ou effectuer toute autre opération nécessaire
        Oeuf o = oeufRepository.save(oeuf);
        return oeufRepository.existsById(o.getId());
    }  


    @PostMapping("/ajouterOeuf")
    @ResponseBody
    public  String ajouterOeuf() {
        int nombreOeufsCrees = 0;

        // Créer 20 œufs
        for (int i = 0; i < 20; i++) {
            boolean succes = genererOeuf();
            if (succes) {
                nombreOeufsCrees++;
            }
        }
        return nombreOeufsCrees + " œufs ont été créés avec succès.";
    }   

    public Boolean genererIncubateur() {
        Random random = new Random();

        int prix = random.nextInt(2001) + 1000;  // Entre 0 et 2000 + 1000

        Incubateur incubateur = new Incubateur();
        incubateur.setPrix(prix);

        incubateurRepository.save(incubateur);
        return incubateurRepository.existsById(incubateur.getId());
    }  

    @PostMapping("/ajouterIncubateur")
    @ResponseBody
    public String ajouterIncubateur() {
        int nombreOeufIncubateur = 0;

        // Créer 20 œufs
        for (int i = 0; i < 10; i++) {
            boolean succes = genererIncubateur();
            if (succes) {
                nombreOeufIncubateur++;
            }
        }
        return nombreOeufIncubateur + " œufs ont été créés avec succès.";
    }  


    @DeleteMapping("/enleverIncubateur")
    public ResponseEntity<String> enleverIncubateur(@RequestBody Map<String, Integer> requestBody) {
        Integer id = requestBody.get("id");
        Optional<Incubateur> i = incubateurRepository.findById(id);
        System.out.println("ta mere");
        i.ifPresent(incubateur -> {
            incubateurRepository.delete(incubateur);
        });
        return ResponseEntity.ok("Incubateur supprimé avec succès");
    }

    @DeleteMapping("/enleverOeuf")
    public ResponseEntity<String> enleverOeuf(@RequestBody Map<String, Integer> requestBody) {
        Integer id = requestBody.get("id");
        Optional<Oeuf> i = oeufRepository.findById(id);
        System.out.println("ta mere");
        i.ifPresent(oeuf -> {
            oeufRepository.delete(oeuf);
        });
        return ResponseEntity.ok("Incubateur supprimé avec succès");
    }


}