package code.inventairemonstre.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventaireMonstreRepository extends JpaRepository<InventaireMonstre, Integer> {
    boolean existsById(Integer id);
    List<InventaireMonstre> findByIdentifiantHero(String identifiantHero);
}
