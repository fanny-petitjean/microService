package code.stockage.controller;

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

import code.stockage.model.StockageExterne;
import code.stockage.model.StockageExterneRepository;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerStockage {
    @Autowired
    StockageExterneRepository stockageExterneRepository;

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

    @PostMapping("/listerMonstre")
    @ResponseBody
    public List<StockageExterne> listerMonstre(@RequestParam MultiValueMap<String, String> formData) {
        String pseudo = formData.getFirst("pseudo");
        List<StockageExterne> i = stockageExterneRepository.findByIdentifiantHero(pseudo);
        return i;
    }

  @DeleteMapping("/enleverMonstre")
@ResponseBody
public Boolean enleverMonstre(@RequestBody Map<String, Object> formData) {
    String id = String.valueOf(formData.get("id"));
    Integer id2 = Integer.parseInt(id);

    Optional<StockageExterne> incubateurOptional = stockageExterneRepository.findById(id2);

    if (incubateurOptional.isPresent()) {
        StockageExterne incubateur = incubateurOptional.get();
        stockageExterneRepository.delete(incubateur);
        return true;
    } else {
        return false; 
    }
}

    @PostMapping("/ajouterMonstre")
    @ResponseBody
    public StockageExterne ajouterMonstre(@RequestBody Map<String, String> formData) {
        String pseudo = formData.get("pseudo");
        String name = formData.get("name");
        String type = formData.get("type");
        Integer identifiantMonstre = Integer.parseInt(formData.get("identifiantMonstre"));
        Integer xp = Integer.parseInt(formData.get("xp"));
        Integer niveau = Integer.parseInt(formData.get("niveau"));

        StockageExterne stockageExterne = new StockageExterne(identifiantMonstre, name, type, niveau, xp, pseudo);
        stockageExterneRepository.save(stockageExterne);
        return stockageExterne;
    } 

}