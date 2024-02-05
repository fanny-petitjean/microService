package code.inventaireincubateur.model;

import javax.print.DocFlavor.STRING;

import jakarta.persistence.*;

@Entity
public class InventaireIncubateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventaireIncubateur_generator")
    @SequenceGenerator(name = "inventaireIncubateur_generator", sequenceName = "inventaireIncubateur_seq", allocationSize = 1)
    private Integer id;

    private Integer identifiantIncubateur;

    private String identifiantHero;

    public InventaireIncubateur(Integer identifiantIncubateur, String identifiantHero) {
        this.identifiantHero = identifiantHero;
        this.identifiantIncubateur = identifiantIncubateur;
    }

    public InventaireIncubateur() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInventaireId() {
        return identifiantIncubateur;
    }

    public void setIventaireId(Integer identifiantIncubateur) {
        this.identifiantIncubateur = identifiantIncubateur;
    }

    public String getHeroId() {
        return identifiantHero;
    }

    public void setIdentifiantHero(String heroId) {
        this.identifiantHero = heroId;
    }
}
