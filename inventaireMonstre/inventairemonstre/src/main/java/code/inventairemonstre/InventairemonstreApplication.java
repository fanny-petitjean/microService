package code.inventairemonstre;

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

	@Bean
    public CommandLineRunner loadData(InventaireMonstreRepository repository) {
        return (args) -> {
            if (!repository.existsById(1)) {
				InventaireMonstre oeuf = new InventaireMonstre(1,"test","Test",15,15,2);
				repository.save(oeuf);
				System.out.println("Oeuf added to the database.");
			} else {
				System.out.println("Oeuf already exists in the database.");
			}
        };
    }

}
