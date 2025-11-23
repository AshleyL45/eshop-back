package com.greta.eshop_api.exposition.dtos;

public class ProductDTO {

    private Long id;
    private String name;
    private String scientificName;
    private String description;
    private String longDescription;

    private double price;
    private String imageUrl;
    private int stockQuantity;
    private double rating;

    private boolean active;
    private double discount;

    private String expertAdvice;

    private Long categoryId;
    private String categoryName;

    public ProductDTO() {}


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

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getRating() {
        return rating;
    }

    public boolean isActive() {
        return active;
    }

    public double getDiscount() {
        return discount;
    }

    public String getExpertAdvice() {
        return expertAdvice;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
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

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setExpertAdvice(String expertAdvice) {
        this.expertAdvice = expertAdvice;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
