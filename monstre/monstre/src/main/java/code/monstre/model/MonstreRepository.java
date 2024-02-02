package code.monstre.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonstreRepository extends JpaRepository<Monstre, Integer> {
    boolean existsByNom(String nom);
}