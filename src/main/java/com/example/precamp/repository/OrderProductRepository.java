package com.example.precamp.repository;

import com.example.precamp.domain.Order;
import com.example.precamp.domain.OrderProduct;
import com.example.precamp.domain.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//
//@Repository
//@RequiredArgsConstructor
//public class OrderProductRepository {
//    private final EntityManager em;
//
//    public void save(OrderProduct orderProduct) {
//        em.persist(orderProduct);
//    }
//}

public interface OrderProductRepository  extends JpaRepository<OrderProduct, Long> {
}