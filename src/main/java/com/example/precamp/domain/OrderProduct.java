package com.example.precamp.domain;

import jakarta.persistence.*;

@Entity
public class OrderProduct {

    @Id @GeneratedValue
    @Column(name = "orderProduct_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int orderQuantity;
}
