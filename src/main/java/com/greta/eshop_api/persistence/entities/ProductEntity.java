package com.greta.eshop_api.persistence.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name = "care_info_id", nullable = false)
    private CareInfoEntity careInfo;

    @ManyToOne
    @JoinColumn(name = "botanical_info_id", nullable = false)
    private BotanicalInfoEntity botanicalInfo;



    @ElementCollection
    @CollectionTable(name = "product_size_options", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "size_option")
    private List<String> sizeOptions;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String expertAdvice;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private double discount = 0.0;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column
    private LocalDate promoStart;

    @Column
    private LocalDate promoEnd;

    @OneToMany(mappedBy = "product")
    private List<OrderItemEntity> orderItems;

    @OneToMany(mappedBy = "product")
    private List<CartItemEntity> cartItems;

    @OneToMany(mappedBy = "product")
    private List<FavoriteEntity> favorites;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews = new ArrayList<>();

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
        return id; }

    public void setId(Long id) {
        this.id = id; }

    public String getName() {
        return name; }

    public void setName(String name) {
        this.name = name; }

    public String getScientificName() {
        return scientificName; }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName; }

    public String getDescription() {
        return description; }

    public void setDescription(String description) {
        this.description = description; }

    public String getLongDescription() {
        return longDescription; }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription; }

    public double getPrice() {
        return price; }

    public void setPrice(double price) {
        this.price = price; }

    public String getImageUrl() {
        return imageUrl; }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl; }

    public CategoryEntity getCategory() {
        return category; }

    public void setCategory(CategoryEntity category) {
        this.category = category; }

    public int getStockQuantity() {
        return stockQuantity; }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity; }

    public double getRating() {
        return rating; }

    public void setRating(double rating) {
        this.rating = rating; }

    public CareInfoEntity getCareInfo() {
        return careInfo; }

    public void setCareInfo(CareInfoEntity careInfo) {
        this.careInfo = careInfo; }

    public BotanicalInfoEntity getBotanicalInfo() {
        return botanicalInfo; }

    public void setBotanicalInfo(BotanicalInfoEntity botanicalInfo) {
        this.botanicalInfo = botanicalInfo; }

    public List<String> getSizeOptions() {
        return sizeOptions; }

    public void setSizeOptions(List<String> sizeOptions) {
        this.sizeOptions = sizeOptions; }

    public String getExpertAdvice() {
        return expertAdvice; }

    public void setExpertAdvice(String expertAdvice) {
        this.expertAdvice = expertAdvice; }

    public boolean isActive() {
        return active; }

    public void setActive(boolean active) {
        this.active = active; }

    public double getDiscount() {
        return discount; }

    public void setDiscount(double discount) {
        this.discount = discount; }

    public LocalDateTime getCreatedAt() {
        return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() {
        return updatedAt; }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt; }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems; }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems; }

    public List<CartItemEntity> getCartItems() {
        return cartItems; }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems; }

    public List<FavoriteEntity> getFavorites() {
        return favorites; }

    public void setFavorites(List<FavoriteEntity> favorites) {
        this.favorites = favorites; }

    public List<ReviewEntity> getReviews() {
        return reviews; }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews; }

    public LocalDate getPromoStart() {
        return promoStart;
    }

    public void setPromoStart(LocalDate promoStart) {
        this.promoStart = promoStart;
    }

    public LocalDate getPromoEnd() {
        return promoEnd;
    }

    public void setPromoEnd(LocalDate promoEnd) {
        this.promoEnd = promoEnd;
    }
}
