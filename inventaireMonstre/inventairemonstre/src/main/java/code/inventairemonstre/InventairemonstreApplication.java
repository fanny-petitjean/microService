package code.inventairemonstre;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import code.inventairemonstre.model.InventaireMonstre;
import code.inventairemonstre.model.InventaireMonstreRepository;


@SpringBootApplication
public class InventairemonstreApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventairemonstreApplication.class, args);
	}

	/*@Bean
    public CommandLineRunner loadData(InventaireMonstreRepository repository) {
        return (args) -> {
			List<InventaireMonstre> inventaireMonstre = repository.findAll();

            if (inventaireMonstre.isEmpty()) {
				InventaireMonstre oeuf = new InventaireMonstre(1,"ddd","dddd",15,15,"kll");
				repository.save(oeuf);

				InventaireMonstre e = new InventaireMonstre(1,"eee","eee",15,15,"kll");
				repository.save(e);
				InventaireMonstre monstre = new InventaireMonstre(1,"test","Test",15,15,"kll");
				repository.save(monstre);
				System.out.println("Monstre added to the database.");
			} else {
				System.out.println("Monstre already exists in the database.");
			}
        };
    }*/

}
