package com.example.precamp.dto;

import com.example.precamp.domain.Order;
import lombok.Getter;

@Getter
public class OrderCreateResponseDto {
    private Long orderId;
    private String productName;

    public OrderCreateResponseDto(Order order) {
        this.orderId = order.getId();
        this.productName = order.getOrderProducts().getFirst().getProduct().getName();
    }
}
