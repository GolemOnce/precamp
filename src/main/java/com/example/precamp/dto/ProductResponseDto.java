package com.example.precamp.dto;

import com.example.precamp.domain.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private int price;
    private int stock;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }
}
