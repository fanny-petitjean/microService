package code.boutique.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import org.springframework.ui.Model;

import code.boutique.model.Incubateur;
import code.boutique.model.IncubateurRepository;
import code.boutique.model.Oeuf;
import code.boutique.model.OeufRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("pseudo")
public class controllerPages {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OeufRepository oeufRepository;
    @Autowired
    private IncubateurRepository incubateurRepository;

    @Autowired
    public controllerPages(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    
    @GetMapping("/")
    public String login() {
        return "login";
    }

    @GetMapping("/boutique")
    public String boutique(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String pseudo = (String) session.getAttribute("pseudo");
        System.out.println(pseudo);
        if (pseudo != null && !pseudo.isEmpty()) {
            model.addAttribute("pseudo", pseudo);
            List<Oeuf> oeufs = oeufRepository.findAll(); // Récupère tous les oeufs de la base de données
            model.addAttribute("tabOeuf", oeufs);
            List<Incubateur> incubateurs = incubateurRepository.findAll(); // Récupère tous les oeufs de la base de données
            model.addAttribute("tabIncubateur", incubateurs);   
            return "boutique";
        } else {
            // Rediriger vers la page d'accueil si le pseudo n'est pas présent
            return "redirect:/";
        }
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