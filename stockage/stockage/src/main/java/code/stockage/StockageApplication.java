package code.stockage;

import java.util.List;

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
	
	/*@Bean
    public CommandLineRunner loadData(StockageExterneRepository repository) {
        return (args) -> {
			List<StockageExterne> list = repository.findAll();
            if (list.isEmpty()) {
				StockageExterne stockage = new StockageExterne(1,"Cheval","aquatique",15,12,"kll");
				StockageExterne stockage1 = new StockageExterne(1,"Voidon","aquatique",15,12,"kll");
				StockageExterne stockage2 = new StockageExterne(1,"Viusn","aquatique",15,12,"kll");
				repository.save(stockage);
				repository.save(stockage1);
				repository.save(stockage2);
				System.out.println("stockage added to the database.");
			} else {
				System.out.println("stockage already exists in the database.");
			}
        };
    }*/

}
