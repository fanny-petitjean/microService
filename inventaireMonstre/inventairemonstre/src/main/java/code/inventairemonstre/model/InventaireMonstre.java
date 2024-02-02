package code.inventairemonstre.model;

import jakarta.persistence.*;

@Entity
public class InventaireMonstre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventaireMonstre_generator")
    @SequenceGenerator(name = "inventaireMonstre_generator", sequenceName = "inventaireMonstre_seq", allocationSize = 1)
    private Integer id;

    private Integer identifiantMonstre;

    private String name;
    private String type;

    private Integer niveau;

    private Integer xp;

    private Integer identifiantHero;

    public InventaireMonstre(Integer identifiantMonstre, String name, String type, Integer niveau, Integer xp, Integer identifiantHero) {
        this.identifiantHero = identifiantHero;
        this.name = name;
        this.type = type;
        this.niveau = niveau;
        this.xp = xp;
        this.identifiantMonstre = identifiantMonstre;
    }

    public InventaireMonstre() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMonstreId() {
        return identifiantMonstre;
    }

    public void setMonstreId(Integer monstreId) {
        this.identifiantMonstre = monstreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getHeroId() {
        return identifiantHero;
    }

    public void setIdentifiantHero(Integer heroId) {
        this.identifiantHero = heroId;
    }
}
