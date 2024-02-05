package code.inventairemonstre.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
 
    // afficher les infos Monstre
    @PostMapping("/listerMonstre")
    @ResponseBody
    public List<InventaireMonstre> listerMonstre(@RequestParam MultiValueMap<String, String> formData) {
        String pseudo = formData.getFirst("pseudo");
        List<InventaireMonstre> i = inventaireMonstreRepository.findByIdentifiantHero(pseudo);
        return i;
    }

    @PostMapping("/enleverMonstre")
    @ResponseBody
    public Boolean enleverMonstre(@RequestBody Map<String, Object> formData) {
        String id = String.valueOf(formData.get("id"));
        Integer id2 = Integer.parseInt(id);

        Optional<InventaireMonstre> incubateurOptional = inventaireMonstreRepository.findById(id2);

        if (incubateurOptional.isPresent()) {
            InventaireMonstre incubateur = incubateurOptional.get();
            inventaireMonstreRepository.delete(incubateur);
            return true;
        } else {
            return false; // L'incubateur avec l'ID spécifié n'a pas été trouvé
        }
    }
    @PostMapping("/ajouterMonstre")
    @ResponseBody
    public InventaireMonstre ajouterMonstre(@RequestBody Map<String, String> formData) {
        String pseudo = formData.get("pseudo");
        String name = formData.get("name");
        String type = formData.get("type");
        Integer identifiantMonstre = Integer.parseInt(formData.get("identifiantMonstre"));
        Integer xp = Integer.parseInt(formData.get("xp"));
        Integer niveau = Integer.parseInt(formData.get("niveau"));

        InventaireMonstre inv = new InventaireMonstre(identifiantMonstre, name, type, niveau, xp, pseudo);
        inventaireMonstreRepository.save(inv);
        return inv;
    }
}