package code.inventaireincubateur.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventaireIncubateurRepository extends JpaRepository<InventaireIncubateur, Integer> {
    Optional<InventaireIncubateur> findById(Integer id);
    List<InventaireIncubateur> findByIdentifiantHero(String identifiantHero);
}
