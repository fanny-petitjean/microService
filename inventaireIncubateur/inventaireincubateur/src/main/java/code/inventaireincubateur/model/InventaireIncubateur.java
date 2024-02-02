package code.inventaireincubateur.model;

import jakarta.persistence.*;

@Entity
public class InventaireIncubateur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventaireIncubateur_generator")
    @SequenceGenerator(name = "inventaireIncubateur_generator", sequenceName = "inventaireIncubateur_seq", allocationSize = 1)
    private Integer id;

    private Integer identifiantIncubateur;

    private Integer identifiantHero;

    public InventaireIncubateur(Integer identifiantIncubateur, Integer identifiantHero) {
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

    public Integer getHeroId() {
        return identifiantHero;
    }

    public void setIdentifiantHero(Integer heroId) {
        this.identifiantHero = heroId;
    }
}
