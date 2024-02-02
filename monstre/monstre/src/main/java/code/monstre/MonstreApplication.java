package code.monstre;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import code.monstre.model.Monstre;
import code.monstre.model.MonstreRepository;

@SpringBootApplication
public class MonstreApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonstreApplication.class, args);
	}

	@Bean
    public CommandLineRunner loadData(MonstreRepository repository) {
        return (args) -> {
            if (!repository.existsById(1)) {
				Monstre monstre = new Monstre("Aquador","Aquatique",15,12);
				repository.save(monstre);
				System.out.println("Monstre added to the database.");
			} else {
				System.out.println("Monstre already exists in the database.");
			}
        };
    }

}
