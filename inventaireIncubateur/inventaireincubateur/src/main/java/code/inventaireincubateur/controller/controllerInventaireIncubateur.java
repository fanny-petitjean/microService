package code.inventaireincubateur.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import code.inventaireincubateur.model.InventaireIncubateur;
import code.inventaireincubateur.model.InventaireIncubateurRepository;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerInventaireIncubateur {

    @Autowired
    InventaireIncubateurRepository inventaireIncubateurRepo;

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

    @PostMapping("/ajouterIncubateur")
    @ResponseBody
    public Boolean ajouterIncubateur(@RequestBody Map<String, Object> formData) {
        System.out.println(formData);
        String pseudo = (String) formData.get("pseudo");
        String id = String.valueOf(formData.get("id"));
        Integer id2 = Integer.parseInt(id);
        List<InventaireIncubateur> i = inventaireIncubateurRepo.findByIdentifiantHero(pseudo);
        System.out.println(i);
        System.out.println("siiize" + i.size());
        if(i.size()<6){
            InventaireIncubateur invent = new InventaireIncubateur(id2,pseudo);
            inventaireIncubateurRepo.save(invent);
            return true;
        }else{
            return false;
        }
    }

    @PostMapping("/enleverIncubateur")
    @ResponseBody
    public Boolean enleverIncubateur(@RequestBody Map<String, Object> formData) {
        System.out.println(formData);
        String id = String.valueOf(formData.get("id"));
        Integer id2 = Integer.parseInt(id);

        Optional<InventaireIncubateur> incubateurOptional = inventaireIncubateurRepo.findById(id2);

        if (incubateurOptional.isPresent()) {
            InventaireIncubateur incubateur = incubateurOptional.get();
            inventaireIncubateurRepo.delete(incubateur);
            return true;
        } else {
            return false; // L'incubateur avec l'ID spécifié n'a pas été trouvé
        }
    }
    
    @PostMapping("/listerIncubateur")
    @ResponseBody
    public List<InventaireIncubateur> listerIncubateur(@RequestParam MultiValueMap<String, String> formData) {
        String pseudo = formData.getFirst("pseudo");
        List<InventaireIncubateur> i = inventaireIncubateurRepo.findByIdentifiantHero(pseudo);
        return i;
    }

}