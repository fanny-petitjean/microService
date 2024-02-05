package code.stockage.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockageExterneRepository extends JpaRepository<StockageExterne, Integer> {
    boolean existsById(Integer id);
    boolean existsByidentifiantMonstreAndIdentifiantHero(Integer identifiantMonstre, String identifiantHero);
    List<StockageExterne> findByIdentifiantHero(String identifiantHero);
}
