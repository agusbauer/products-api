package com.goteam.productsapi.products.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
public class Product {

    @Id
    private  String id;
    private  String name;
    private  BigDecimal price;
    private  String description;
    private  String imageUrl;
    @JsonProperty("created_at")
    private  LocalDateTime createdAt;

    private Product(){}

    public Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.description = builder.description;
        this.imageUrl = builder.imageUrl;
        this.createdAt = LocalDateTime.now(ZoneOffset.UTC);

    }

    public String getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public static class Builder{

        private  String id;
        private  String name;
        private  BigDecimal price;
        private  String description;
        private  String imageUrl;

        public Builder(String id, String name){
            this.id = id;
            this.name = name;
        }

        public Builder withPrice(BigDecimal price){
            this.price = price;
            return this;
        }

        public Builder withImage(String imageUrl){
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder withDescription(String description){
            this.description = description;
            return this;
        }

        public Product build(){
            return new Product(this);
        }

    }
}
