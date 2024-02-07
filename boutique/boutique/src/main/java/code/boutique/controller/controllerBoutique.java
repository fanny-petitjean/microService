package code.boutique.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

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
    public List<Oeuf> listerOeuf(@RequestParam MultiValueMap<String, String> nbO) {
        List<Oeuf> oeufs = oeufRepository.findAll();
        return oeufs;
    }



    @PostMapping("/listerIncubateur")
    @ResponseBody
    public List<Incubateur> listerIncubateur(@RequestParam MultiValueMap<String, String> nbIn) {
        Integer nbIncubateur = Integer.parseInt(nbIn.getFirst("nbIncubateur"));
        Integer nb = 6-nbIncubateur;
        List<Incubateur> incubateurs = incubateurRepository.findAll();
        System.out.println(nb);
        System.out.println(incubateurs);
        // Vérifiez si le nombre demandé est supérieur au nombre d'incubateurs disponibles dans la base de données
        if (nb > incubateurs.size()) {
            // Gérer le cas où le nombre demandé est supérieur au nombre d'incubateurs disponibles
            throw new IllegalArgumentException("Le nombre d'incubateurs demandé est supérieur au nombre total d'incubateurs disponibles.");
        }

        // Créez une liste pour stocker les incubateurs choisis aléatoirement
        List<Incubateur> incubateursChoisis = new ArrayList<>();


        for (int i = 0; i < nb; i++) {
            incubateursChoisis.add(incubateurs.get(i));
        }

        return incubateursChoisis;
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


    public Boolean genererIncubateur() {
        Random random = new Random();

        int prix = random.nextInt(2001) + 1000;  // Entre 0 et 2000 + 1000

        Incubateur incubateur = new Incubateur();
        incubateur.setPrix(prix);

        incubateurRepository.save(incubateur);
        return incubateurRepository.existsById(incubateur.getId());
    }  

    public void supprimerTousLesOeufs() {
        oeufRepository.deleteAll();
    }
    
    public void supprimerTousLesIncubateurs() {
        incubateurRepository.deleteAll();
    }
    
    @GetMapping("/miseAJourBoutique")
    @ResponseBody
    public String miseAJourBoutique() {    
        supprimerTousLesOeufs();
        supprimerTousLesIncubateurs();
        int nombreOeufIncubateur =0;
    

        // Créer 20 œufs
        for (int i = 0; i < 6; i++) {
            boolean succes = genererIncubateur();
            if (succes) {
                nombreOeufIncubateur++;
            }
        }

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


    @DeleteMapping("/enleverIncubateur")
    public ResponseEntity<String> enleverIncubateur(@RequestBody Map<String, Integer> requestBody) {
        Integer id = requestBody.get("id");
        Optional<Incubateur> i = incubateurRepository.findById(id);
        i.ifPresent(incubateur -> {
            incubateurRepository.delete(incubateur);
        });
        return ResponseEntity.ok("Incubateur supprimé avec succès");
    }

    @DeleteMapping("/enleverOeuf")
    public ResponseEntity<String> enleverOeuf(@RequestBody Map<String, Integer> requestBody) {
        Integer id = requestBody.get("id");
        Optional<Oeuf> i = oeufRepository.findById(id);
        i.ifPresent(oeuf -> {
            oeufRepository.delete(oeuf);
        });
        return ResponseEntity.ok("Incubateur supprimé avec succès");
    }


}