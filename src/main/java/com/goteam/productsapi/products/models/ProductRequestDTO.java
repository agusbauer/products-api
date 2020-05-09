package com.goteam.productsapi.products.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProductRequestDTO {

    @NotNull
    private final BigDecimal price;

    @NotNull
    private final String description;

    @NotNull
    @NotEmpty
    private final String name;

    @JsonProperty("image_url")
    private final String imageUrl;

    public ProductRequestDTO(BigDecimal price, String description, String name,String imageUrl) {
        this.price = price;
        this.description = description;
        this.name = name;
        this.imageUrl = imageUrl;
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

    @Override
    public String toString() {
        return "ProductRequestDTO{" +
                "price=" + price +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
