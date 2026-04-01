package com.example.precamp.dto;

import com.example.precamp.domain.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long productId;
    private String name;
    private int price;
    private int stock;

    public ProductResponseDto(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }
}
