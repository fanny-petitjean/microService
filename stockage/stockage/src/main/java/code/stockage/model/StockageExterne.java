package code.stockage.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class StockageExterne {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stockage_generator")
    @SequenceGenerator(name = "stockage_generator", sequenceName = "stockage_seq", allocationSize = 1)
    private Integer id;
    private Integer identifiantMonstre;

    private String identifiantHero;

    private String name;
    private String type;
    private int niveau;
    private int xp;


    public StockageExterne() {
        // Constructeur par défaut nécessaire pour JPA
    }

    public StockageExterne(Integer identifiantMonstre, String nomMonstre, String type, int niveau, int xp, String identifiantHero) {
        this.name = nomMonstre;
        this.identifiantMonstre = identifiantMonstre;
        this.type = type;
        this.niveau = niveau;
        this.xp = xp;
        this.identifiantHero = identifiantHero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getIdentifiantMonstre() {
        return identifiantMonstre;
    }

    public void setIdentifiantMonstre(Integer identifiantMonstre) {
        this.identifiantMonstre = identifiantMonstre;
    }

    public String getName() {
        return name;
    }

    public void setName(String nomMonstre) {
        this.name = nomMonstre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getHero() {
        return identifiantHero;
    }

    public void setHero(String hero) {
        this.identifiantHero = hero;
    }
}
