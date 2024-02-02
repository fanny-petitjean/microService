package code.inventaireoeuf.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventaireOeufRepository extends JpaRepository<InventaireOeuf, Integer> {
    boolean existsById(Integer id);
}
