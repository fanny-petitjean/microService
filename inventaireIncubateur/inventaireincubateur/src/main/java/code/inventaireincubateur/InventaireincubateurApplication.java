package code.inventaireincubateur;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import code.inventaireincubateur.model.InventaireIncubateur;
import code.inventaireincubateur.model.InventaireIncubateurRepository;

@SpringBootApplication
public class InventaireincubateurApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventaireincubateurApplication.class, args);
	}

	@Bean
    public CommandLineRunner loadData(InventaireIncubateurRepository incubateur) {
        return (args) -> {
            if (!incubateur.existsById(1)) {
				InventaireIncubateur iv = new InventaireIncubateur(1,"jean");
				incubateur.save(iv);
				System.out.println("Inventaire Incubateur added to the database.");
			} else {
				System.out.println("Inventaire Incubateur already exists in the database.");
			}
        };
    }

}
