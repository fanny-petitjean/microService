package code.boutique.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import org.springframework.ui.Model;
import code.boutique.model.Oeuf;

@Controller
public class controllerPages {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public controllerPages(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    
    @GetMapping("/")
    public String login() {
        return "login";
    }

    
    @GetMapping("/home")
    public String home() {
        return "boutique";
    }
    @GetMapping("/boutique")
    public String boutique(Model model) {
          //demander db les oeufs et les incubateurs
        // tout mettre dans la meme variable

        // Exemple de données de produits
        List<Oeuf> tabOeuf = new ArrayList<>();
        tabOeuf.add(new Oeuf(1,12));
        tabOeuf.add(new Oeuf(2,15));
        tabOeuf.add(new Oeuf(2,15));
        tabOeuf.add(new Oeuf(58,15));
        tabOeuf.add(new Oeuf(52,15));
        tabOeuf.add(new Oeuf(2,15));
        tabOeuf.add(new Oeuf(3,16));
        tabOeuf.add(new Oeuf(4,17));
        tabOeuf.add(new Oeuf(4,17));
        tabOeuf.add(new Oeuf(4,17));

        model.addAttribute("tabOeuf", tabOeuf);
        return "boutique";
    }

    @GetMapping("/inventaire")
    public String inventaire() {
        return "inventaire";
    }
    @GetMapping("/inventaire_monstre")
    public String inventaire_monstre() {
        return "inventaire_monstre";
    }
    @GetMapping("/vente")
    public String vente() {
        return "vente";
    }
    @GetMapping("/incubation")
    public String incubation() {
        return "incubateur";
    }
    @GetMapping("/compte")
    public String compte() {
        return "compte";
    }
     
}