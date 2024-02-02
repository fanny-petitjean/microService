package code.incubateuroeuf.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubateurOeufRepository extends JpaRepository<IncubateurOeuf, Integer> {
    boolean existsByIdentifiantIncubateurAndIdentifiantHeroAndIdentifiantOeuf(Integer identifiantIncubateur, Long identifiantHero, Long identifiantOeuf);
}
