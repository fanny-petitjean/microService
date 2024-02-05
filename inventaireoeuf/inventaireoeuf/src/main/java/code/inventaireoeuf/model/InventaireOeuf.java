package code.inventaireoeuf.model;

import jakarta.persistence.*;

@Entity
public class InventaireOeuf {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventaireOeuf_generator")
    @SequenceGenerator(name = "inventaireOeuf_generator", sequenceName = "inventaireOeuf_seq", allocationSize = 1)
    private Integer id;

    private Integer identifiantOeuf;

    private String identifiantHero;
    private Integer dureeEclosion;

    public InventaireOeuf(Integer identifiantOeuf, String identifiantHero, Integer duree) {
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

    public String getHeroId() {
        return identifiantHero;
    }

    public void setIdentifiantHero(String heroId) {
        this.identifiantHero = heroId;
    }
}
