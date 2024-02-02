package code.heros;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import code.heros.model.Hero;
import code.heros.model.HeroRepository;

@SpringBootApplication
public class HerosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HerosApplication.class, args);
	}

	@Bean
    public CommandLineRunner loadData(HeroRepository repository) {
        return (args) -> {
            if (!repository.existsById(1)) {
				Hero oeuf = new Hero(10, "hugo");
				repository.save(oeuf);
				System.out.println("Oeuf added to the database.");
			} else {
				System.out.println("Oeuf already exists in the database.");
			}
        };
    }

}
