package com.gdrc.market.domain;

public class ProductMapper {
    private int productId;
    private String name;
    private int categoryIds;
    private double price;
    private int stock;
    private boolean active;
    private CategoryMapper category;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(int categoryIds) {
        this.categoryIds = categoryIds;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CategoryMapper getCategory() {
        return category;
    }

    public void setCategory(CategoryMapper category) {
        this.category = category;
    }
}
