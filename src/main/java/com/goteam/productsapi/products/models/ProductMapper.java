package com.goteam.productsapi.products.models;

import java.util.UUID;

public class ProductMapper {

    public static Product requestToProduct(ProductRequestDTO requestDTO){

        String id = UUID.randomUUID().toString();
        return new Product.Builder(id,requestDTO.getName())
                .withPrice(requestDTO.getPrice())
                .withDescription(requestDTO.getDescription())
                .withImage(requestDTO.getImageUrl())
                .build();
    }
}
