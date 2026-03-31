package com.example.precamp.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* 주문 하나당 상품 하나라 @ManyToOne으로 구현 가능하지만, 실무에선 거의 @ManyToMany일 것이고,
 OrderProduct를 통해 N+1를 유도해 N+1 문제 예방을 위한 코드 실습하기 위해 중간 테이블 추가함.
*/
@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public static OrderProduct create(Order order, Product product, int orderQuantity) {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setOrderPrice(product.getPrice() *  orderQuantity);
        orderProduct.setOrderQuantity(orderQuantity);
        order.getOrderProducts().add(orderProduct); // 메모리상 객체 동기화 clear(), flush() 제거
        return orderProduct;
    }
}
