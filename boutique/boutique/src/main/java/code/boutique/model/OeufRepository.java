package code.boutique.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OeufRepository extends JpaRepository<Oeuf, Integer> {
    boolean existsById(Integer id);
    boolean existsOeufBy();
}
