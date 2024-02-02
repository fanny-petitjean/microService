package code.boutique.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubateurRepository extends JpaRepository<Incubateur, Integer> {
    boolean existsByIdentifiantIncubateur(Integer identifiantIncubateur);
}
