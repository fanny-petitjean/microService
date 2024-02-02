package code.stockage;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import code.stockage.model.StockageExterne;
import code.stockage.model.StockageExterneRepository;

@SpringBootApplication
public class StockageApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockageApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner loadData(StockageExterneRepository repository) {
        return (args) -> {
            if (!repository.existsByidentifiantMonstreAndIdentifiantHero(1,1)) {
				StockageExterne stockage = new StockageExterne(1,"Cheval","aquatique",15,12,1);
				repository.save(stockage);
				System.out.println("stockage added to the database.");
			} else {
				System.out.println("stockage already exists in the database.");
			}
        };
    }

}
