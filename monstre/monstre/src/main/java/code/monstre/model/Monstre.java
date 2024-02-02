package code.monstre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Monstre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "monstre_generator")
    @SequenceGenerator(name = "monstre_generator", sequenceName = "monstre_seq", allocationSize = 1)
    private Integer id;

    private String nom;
    private String type;
    private int niveau;
    private int xp;

    public Monstre() {
        // Constructeur par défaut nécessaire pour JPA
    }

    public Monstre(String nom, String type, int niveau, int xp) {
        this.nom = nom;
        this.type = type;
        this.niveau = niveau;
        this.xp = xp;
    }

    public Integer getId() {
        return id;
    }

    public void setIdentifiantMonstre(Integer identifiantMonstre) {
        this.id = identifiantMonstre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
}