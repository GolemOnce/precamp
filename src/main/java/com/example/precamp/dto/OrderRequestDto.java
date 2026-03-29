package com.example.precamp.dto;

import lombok.Getter;

@Getter
public class OrderRequestDto {
    private Long productId;
    private int orderQuantity;
}
