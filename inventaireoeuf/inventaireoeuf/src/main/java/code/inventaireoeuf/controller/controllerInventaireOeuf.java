package code.inventaireoeuf.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import code.inventaireoeuf.model.InventaireOeuf;
import code.inventaireoeuf.model.InventaireOeufRepository;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerInventaireOeuf {

    @Autowired
    InventaireOeufRepository inventaireOeufRepository;
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

    @DeleteMapping("/enleverOeuf")
    @ResponseBody
    public Boolean enleverOeuf(@RequestBody Map<String, Object> formData) {
        String id = String.valueOf(formData.get("id"));
        Integer id2 = Integer.parseInt(id);

        Optional<InventaireOeuf> inventaireOptional = inventaireOeufRepository.findById(id2);

        if (inventaireOptional.isPresent()) {
            InventaireOeuf inventaire = inventaireOptional.get();
            inventaireOeufRepository.delete(inventaire);
            return true;
        } else {
            return false; // L'incubateur avec l'ID spécifié n'a pas été trouvé
        }
    }


    @PostMapping("/ajouterOeuf")
    @ResponseBody
    public Boolean ajouterIncubateur(@RequestBody Map<String, Object> formData) {
        System.out.println(formData);
        String pseudo = (String) formData.get("pseudo");
        String id = String.valueOf(formData.get("id"));
        Integer id2 = Integer.parseInt(id);
        String duree = String.valueOf(formData.get("dureeEclosion"));
        Integer dureeEclosion = Integer.parseInt(duree);
        List<InventaireOeuf> i = inventaireOeufRepository.findByIdentifiantHero(pseudo);
        System.out.println(i);
        InventaireOeuf invent = new InventaireOeuf(id2,pseudo,dureeEclosion );
        inventaireOeufRepository.save(invent);
        return true;

    }

    @PostMapping("/listerOeuf")
    @ResponseBody
    public List<InventaireOeuf> listerIncubateur(@RequestParam MultiValueMap<String, String> formData) {
        String pseudo = formData.getFirst("pseudo");
        List<InventaireOeuf> i = inventaireOeufRepository.findByIdentifiantHero(pseudo);
        return i;
    }
}