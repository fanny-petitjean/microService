package code.inventaireoeuf.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventaireOeufRepository extends JpaRepository<InventaireOeuf, Integer> {
    boolean existsById(Integer id);
    Optional<InventaireOeuf> findById(Integer id);
    List<InventaireOeuf> findByIdentifiantHero(String identifiantHero);
}
