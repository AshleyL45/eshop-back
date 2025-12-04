package com.greta.eshop_api.persistence.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "product_care_info")
public class CareInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String watering;
    private String sunlight;
    private String soilType;
    private String fertilizer;

    public Long getId() {
        return id;
    }

    public String getWatering() {
        return watering;
    }

    public String getSunlight() {
        return sunlight;
    }

    public String getSoilType() {
        return soilType;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public void setSunlight(String sunlight) {
        this.sunlight = sunlight;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }
}
