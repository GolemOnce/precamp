package com.example.precamp.domain;

import jakarta.persistence.*;
/* 주문 하나당 상품 하나라 @ManyToOne으로 구현 가능하지만, 실무에선 거의 @ManyToMany일 것이고,
 OrderProduct를 통해 N+1를 유도해 N+1 문제 예방을 위한 코드 실습하기 위해 중간 테이블 추가함.
*/
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
