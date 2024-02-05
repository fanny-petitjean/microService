package code.inventaireoeuf;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import code.inventaireoeuf.model.InventaireOeuf;
import code.inventaireoeuf.model.InventaireOeufRepository;

@SpringBootApplication
public class InventaireoeufApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventaireoeufApplication.class, args);
	}

	@Bean
    public CommandLineRunner loadData(InventaireOeufRepository repository) {
        return (args) -> {
            if (!repository.existsById(1)) {
				InventaireOeuf oeuf = new InventaireOeuf(1,"Jean",12);
				repository.save(oeuf);
				System.out.println("Inventaire Oeuf added to the database.");
			} else {
				System.out.println("Inventaire Oeuf already exists in the database.");
			}
        };
    }

}
