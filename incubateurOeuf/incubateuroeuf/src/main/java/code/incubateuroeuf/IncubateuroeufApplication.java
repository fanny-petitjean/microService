package code.incubateuroeuf;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import code.incubateuroeuf.model.IncubateurOeuf;
import code.incubateuroeuf.model.IncubateurOeufRepository;

@SpringBootApplication
public class IncubateuroeufApplication {

	public static void main(String[] args) {
		SpringApplication.run(IncubateuroeufApplication.class, args);
	}
	@Bean
    public CommandLineRunner loadData(IncubateurOeufRepository repository) {
        return (args) -> {
			List<IncubateurOeuf> incubateurOeuf = repository.findAll();

            if (incubateurOeuf.isEmpty()) {
				LocalDateTime d = LocalDateTime.of(2023, 12, 12, 10, 0);

				IncubateurOeuf oeuf = new IncubateurOeuf(1,"T",1, d);
				repository.save(oeuf);
				System.out.println("Oeuf added to the database.");
			} else {
				System.out.println("Oeuf already exists in the database.");
			}
        };
    }

}
