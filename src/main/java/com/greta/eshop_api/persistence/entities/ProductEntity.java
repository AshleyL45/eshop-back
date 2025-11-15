package com.greta.eshop_api.persistence.entities;

import jakarta.persistence.*;

import java.util.List;


import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false)
    private String scientificName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String longDescription;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Boolean inStock;

    @Column(nullable = false)
    private double rating;

    @Column(nullable = false)
    private String watering;

    @Column(nullable = false)
    private String sunlight;

    @Column(nullable = false)
    private String fertilizer;

    @Column(nullable = false)
    private String soilType;

    @ElementCollection /* table séparée */
    @CollectionTable(name = "product_size_options", joinColumns = @JoinColumn(name = "product_id")) /* configuration table + colonne jointure */
    @Column(name = "size_option")
    private List<String> sizeOptions;

    @Column(nullable = false)
    private String family;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String lifespan;

    @Column(nullable = false)
    private String toxicity;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String expertAdvice;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public ProductEntity() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public String getDescription() {
        return description;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public double getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public double getRating() {
        return rating;
    }

    public String getWatering() {
        return watering;
    }

    public String getSunlight() {
        return sunlight;
    }

    public String getFertilizer() {
        return fertilizer;
    }

    public String getSoilType() {
        return soilType;
    }

    public List<String> getSizeOptions() {
        return sizeOptions;
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

    public String getExpertAdvice() {
        return expertAdvice;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public void setSunlight(String sunlight) {
        this.sunlight = sunlight;
    }

    public void setFertilizer(String fertilizer) {
        this.fertilizer = fertilizer;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public void setSizeOptions(List<String> sizeOptions) {
        this.sizeOptions = sizeOptions;
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

    public void setExpertAdvice(String expertAdvice) {
        this.expertAdvice = expertAdvice;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
