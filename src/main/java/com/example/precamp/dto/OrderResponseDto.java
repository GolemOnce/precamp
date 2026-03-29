package com.example.precamp.dto;

import com.example.precamp.domain.Order;
import com.example.precamp.domain.Product;
import lombok.Getter;

@Getter
public class OrderResponseDto {

    private Long orderId;
    private String productName;
    private int orderQuantity;
    private int orderPrice;

    public OrderResponseDto(Order order) {
        this.orderId = order.getId();
        this.productName = order.getOrderProducts().getFirst().getProduct().getName();
        this.orderQuantity = order.getOrderProducts().getFirst().getOrderQuantity();
        this.orderPrice = order.getOrderProducts().getFirst().getOrderPrice();
    }
}
