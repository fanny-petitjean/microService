package code.boutique;

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
            if (!repository.existsById(1)) {
                Oeuf oeuf = new Oeuf(1, 200);
                repository.save(oeuf);
                System.out.println("Oeuf added to the database.");
            } else {
                System.out.println("Oeuf already exists in the database.");
            }

            if (!incubateur.existsByIdentifiantIncubateur(1)) {
                Incubateur incu = new Incubateur(12);
                incubateur.save(incu);
                System.out.println("Incubateur added to the database.");
            } else {
                System.out.println("Incubateur already exists in the database.");
            }
        };
    }
}