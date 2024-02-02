package code.inventaireoeuf.model;

import jakarta.persistence.*;

@Entity
public class InventaireOeuf {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventaireOeuf_generator")
    @SequenceGenerator(name = "inventaireOeuf_generator", sequenceName = "inventaireOeuf_seq", allocationSize = 1)
    private Integer id;

    private Integer identifiantOeuf;

    private Integer identifiantHero;
    private Integer dureeEclosion;

    public InventaireOeuf(Integer identifiantOeuf, Integer identifiantHero, Integer duree) {
        this.identifiantHero = identifiantHero;
        this.dureeEclosion = duree;
        this.identifiantOeuf = identifiantOeuf;
    }

    public InventaireOeuf() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOeufId() {
        return identifiantOeuf;
    }

    public void setOeufId(Integer oeufId) {
        this.identifiantOeuf = oeufId;
    }

    public Integer getDureeEclosion() {
        return dureeEclosion;
    }

    public void setDureeEclosion(Integer dureeEclosion) {
        this.dureeEclosion = dureeEclosion;
    }

    public Integer getHeroId() {
        return identifiantHero;
    }

    public void setIdentifiantHero(Integer heroId) {
        this.identifiantHero = heroId;
    }
}
