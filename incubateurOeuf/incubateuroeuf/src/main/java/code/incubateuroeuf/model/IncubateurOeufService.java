package code.incubateuroeuf.model;

import code.incubateuroeuf.model.IncubateurOeuf;
import code.incubateuroeuf.model.IncubateurOeufRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class IncubateurOeufService {
        @Autowired
    private IncubateurOeufRepository incubateurOeufRepository;

    public List<IncubateurOeuf> getIncubateursOeufsByHero(String identifiantHero) {
        return incubateurOeufRepository.findAllByIdentifiantHero(identifiantHero)

    // Autres méthodes de service à implémenter...
    }

    public void ajouterOeuf(String identifiantHero, Integer identifiantOeuf) {
        // Implémentez la logique pour ajouter un œuf dans un incubateur
        // Utilisez incubateurOeufRepository pour enregistrer l'œuf dans la base de données
    }

    public void ecloreOeuf(Integer idIncubateurOeuf) {
        // Implémentez la logique pour l'éclosion d'œufs
        // Utilisez incubateurOeufRepository pour mettre à jour l'état de l'œuf dans la base de données
    }
}
    

