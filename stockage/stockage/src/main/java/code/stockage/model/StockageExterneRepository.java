package code.stockage.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockageExterneRepository extends JpaRepository<StockageExterne, Integer> {
    boolean existsById(Integer id);
    boolean existsByidentifiantMonstreAndIdentifiantHero(Integer identifiantMonstre, Integer identifiantHero);
}
