package org.osmanacademy.applicationundertest.dto;

public class ProductDetailsDTO {
    private String name;
    private String description;
    private String imageUrl;
    private String price;

    // Constructor
    public ProductDetailsDTO(String name, String description, String imageUrl, String price) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "InventoryItemDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
