package com.greta.eshop_api.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product_botanical_info")
public class BotanicalInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String family;
    private String origin;
    private String lifespan;
    private String toxicity;
    private String difficulty;

    public Long getId() {
        return id;
    }

    public String getFamily() {
        return family;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLifespan() {
        return lifespan;
    }

    public String getToxicity() {
        return toxicity;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setLifespan(String lifespan) {
        this.lifespan = lifespan;
    }

    public void setToxicity(String toxicity) {
        this.toxicity = toxicity;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
