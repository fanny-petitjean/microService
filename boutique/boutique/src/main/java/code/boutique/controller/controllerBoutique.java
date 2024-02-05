package code.boutique.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import code.boutique.model.Incubateur;
import code.boutique.model.IncubateurRepository;
import code.boutique.model.Oeuf;
import code.boutique.model.OeufRepository;

@Controller
public class controllerBoutique {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OeufRepository oeufRepository;

    @Autowired
    private IncubateurRepository incubateurRepository;

    @Autowired
    public controllerBoutique(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

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
}