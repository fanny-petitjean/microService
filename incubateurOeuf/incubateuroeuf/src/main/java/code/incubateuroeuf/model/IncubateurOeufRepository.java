package code.incubateuroeuf.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncubateurOeufRepository extends JpaRepository<IncubateurOeuf, Integer> {
    boolean existsByIdentifiantIncubateurAndIdentifiantHeroAndIdentifiantOeuf(Integer identifiantIncubateur, String identifiantHero, Integer identifiantOeuf);

    List<IncubateurOeuf> findAllByIdentifiantHero(String identifiantHero);
}

// érifier l'existence d'un enregistrement avec des valeurs spécifiques pour certains attributs.