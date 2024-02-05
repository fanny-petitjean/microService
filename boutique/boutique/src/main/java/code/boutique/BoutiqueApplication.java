package code.boutique;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import code.boutique.model.IncubateurRepository;
import code.boutique.model.Incubateur;
import code.boutique.model.Oeuf;
import code.boutique.model.OeufRepository;

@SpringBootApplication
public class BoutiqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoutiqueApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(OeufRepository repository, IncubateurRepository incubateur) {
        return (args) -> {
            List<Oeuf> oeufs = repository.findAll();
            if (oeufs.isEmpty()) {
                Oeuf oeuf = new Oeuf(1, 200);
                repository.save(oeuf);
                oeuf = new Oeuf(1, 200);
                repository.save(oeuf);
                oeuf = new Oeuf(1, 100);
                repository.save(oeuf);
                oeuf = new Oeuf(1, 350);
                repository.save(oeuf);
                oeuf = new Oeuf(1, 150);
                repository.save(oeuf);
            }
        
            List<Incubateur> incubateurList = incubateur.findAll();
            if (incubateurList.isEmpty()) {
                Incubateur incu = new Incubateur(1200);
                incubateur.save(incu);
                incu = new Incubateur(1500);
                incubateur.save(incu);
                incu = new Incubateur(1000);
                incubateur.save(incu);
                incu = new Incubateur(1600);
                incubateur.save(incu);
                incu = new Incubateur(1200);
                incubateur.save(incu);
                incu = new Incubateur(1550);
                incubateur.save(incu);
            }
        };
    }
}