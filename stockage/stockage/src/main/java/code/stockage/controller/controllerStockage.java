package code.stockage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("") // Définissez le chemin de base pour toutes les méthodes du contrôleur
public class controllerStockage {

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

     // ajouter des monstres 
     @GetMapping("/ajouterMonstres")
     public String ajouterMonstres() {
         String value = "test";
         //ajouter des monstres dans la bd
         //retourner quand c'est ok
         return value;
     } 
}