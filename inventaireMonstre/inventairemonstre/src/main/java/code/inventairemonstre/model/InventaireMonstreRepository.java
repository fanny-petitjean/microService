package code.inventairemonstre.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventaireMonstreRepository extends JpaRepository<InventaireMonstre, Integer> {
    boolean existsById(Integer id);
}
