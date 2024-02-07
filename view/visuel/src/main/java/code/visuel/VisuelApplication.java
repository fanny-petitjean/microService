package code.visuel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VisuelApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisuelApplication.class, args);
	}

}
