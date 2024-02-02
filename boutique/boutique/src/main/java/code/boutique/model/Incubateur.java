package code.boutique.model;

import jakarta.persistence.*;

@Entity
public class Incubateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incubateur_generator")
    @SequenceGenerator(name = "incubateur_generator", sequenceName = "incubateur_seq", allocationSize = 1)
    private Integer identifiantIncubateur;

    private Integer prix;

    public Incubateur(Integer prix) {
        this.prix = prix;
    }

    public Incubateur() {
    }

    public Integer getId() {
        return identifiantIncubateur;
    }

    public void setId(Integer id) {
        this.identifiantIncubateur = id;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

}
