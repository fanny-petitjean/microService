package code.incubateuroeuf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.criteria.CriteriaBuilder.In;

import java.time.LocalDateTime;

@Entity
public class IncubateurOeuf {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hero_generator")
    @SequenceGenerator(name = "hero_generator", sequenceName = "hero_seq", allocationSize = 1)
    private Integer id;

    private Integer identifiantIncubateur;
    private String identifiantHero;
    private Integer identifiantOeuf;

    private LocalDateTime dateFinEclosion;

    public IncubateurOeuf() {
        // Constructeur par défaut nécessaire pour JPA
    }

    public IncubateurOeuf(Integer identifiantIncubateur, String hero, Integer oeuf, LocalDateTime dateFinEclosion) {
        this.identifiantIncubateur = identifiantIncubateur;
        this.identifiantHero = hero;
        this.identifiantOeuf = oeuf;
        this.dateFinEclosion = dateFinEclosion;
    }

    public Integer getId(){
        return id;
    }

    public Integer getIdIncubateur() {
        return identifiantIncubateur;
    }

    public void setIdIncubateur(Integer identifiantIncubateur) {
        this.identifiantIncubateur = identifiantIncubateur;
    }

    public String getHero() {
        return identifiantHero;
    }

    public void setHero(String hero) {
        this.identifiantHero = hero;
    }

    public Integer getIdOeuf() {
        return identifiantOeuf;
    }

    public void setIdOeuf(Integer oeuf) {
        this.identifiantOeuf = oeuf;
    }

    public LocalDateTime getDateFinEclosion() {
        return dateFinEclosion;
    }

    public void setDateFinEclosion(LocalDateTime dateFinEclosion) {
        this.dateFinEclosion = dateFinEclosion;
    }
}
