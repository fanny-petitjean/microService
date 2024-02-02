package code.inventaireincubateur.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventaireIncubateurRepository extends JpaRepository<InventaireIncubateur, Integer> {
    boolean existsById(Integer id);
}
