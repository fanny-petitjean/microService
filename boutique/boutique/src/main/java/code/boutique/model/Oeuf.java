package code.boutique.model;

import jakarta.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Oeuf {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "oeuf_generator")
    @SequenceGenerator(name = "oeuf_generator", sequenceName = "oeuf_seq", allocationSize = 1)
    private Integer id;

    private Integer dureeEclosion;

    private Integer prix;

    public Oeuf(Integer dureeEclosion, Integer prix) {
        this.dureeEclosion = dureeEclosion;
        this.prix = prix;
    }

    public Oeuf() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDureeEclosion() {
        return dureeEclosion;
    }

    public void setDureeEclosion(Integer dureeEclosion) {
        this.dureeEclosion = dureeEclosion;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
}
