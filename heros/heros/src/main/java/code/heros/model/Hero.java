package code.heros.model;

import jakarta.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hero_generator")
    @SequenceGenerator(name = "hero_generator", sequenceName = "hero_seq", allocationSize = 1)
    private Integer id;

    private String name;

    private Integer argent;
    private Integer nbIncubateur;

    public Hero(Integer argent, String name, Integer nbIncubateur) {
        this.argent = argent;
        this.name = name;
        this.nbIncubateur = nbIncubateur;

    }

    public Hero() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArgent() {
        return argent;
    }

    public void setArgent(Integer argent) {
        this.argent = argent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNbIncubateur() {
        return nbIncubateur;
    }

    public void setNbIncubateur(Integer nbIncubateur) {
        this.nbIncubateur = nbIncubateur;
    }


}
