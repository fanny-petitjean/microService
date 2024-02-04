package code.heros.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer> {
    Optional<Hero> findByName(String name);
    boolean existsById(Integer id);

}