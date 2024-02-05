package code.stockage;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Autoriser CORS pour toutes les URL
                .allowedOrigins("*") // Autoriser les requêtes depuis n'importe quelle origine
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autoriser les méthodes HTTP spécifiées
                .allowedHeaders("*"); // Autoriser tous les en-têtes dans la requête
    }
}
