package com.greta.eshop_api.persistence.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(nullable = false)
    private int stockQuantity;

    @Column(nullable = false)
    private double rating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "care_info_id")
    private CareInfoEntity careInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "botanical_info_id")
    private BotanicalInfoEntity botanicalInfo;

    @ElementCollection
    @CollectionTable(name = "product_size_options", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "size_option")
    private List<String> sizeOptions;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String expertAdvice;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product")
    private List<OrderItemEntity> orderItems;

    @OneToMany(mappedBy = "product")
    private List<CartItemEntity> cartItems;

    @OneToMany(mappedBy = "product")
    private List<FavoriteEntity> favorites;

    @OneToMany(mappedBy = "product")
    private List<ReviewEntity> reviews;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public ProductEntity() {}

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

    public CategoryEntity getCategory() {
        return category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getRating() {
        return rating;
    }

    public CareInfoEntity getCareInfo() {
        return careInfo;
    }

    public BotanicalInfoEntity getBotanicalInfo() {
        return botanicalInfo;
    }

    public List<String> getSizeOptions() {
        return sizeOptions;
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

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public List<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public List<FavoriteEntity> getFavorites() {
        return favorites;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
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

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCareInfo(CareInfoEntity careInfo) {
        this.careInfo = careInfo;
    }

    public void setBotanicalInfo(BotanicalInfoEntity botanicalInfo) {
        this.botanicalInfo = botanicalInfo;
    }

    public void setSizeOptions(List<String> sizeOptions) {
        this.sizeOptions = sizeOptions;
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

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public void setFavorites(List<FavoriteEntity> favorites) {
        this.favorites = favorites;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }
}
