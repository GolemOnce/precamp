package com.example.precamp.repository;

import com.example.precamp.domain.Order;
import com.example.precamp.domain.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findByIdWithProducts(Long orderId) {
        em.flush();
        em.clear();

        return em.createQuery(
                "select o from Order o " +
                        "join fetch o.orderProducts op " +
                        "join fetch op.product " +
                        "where o.id = :orderId", Order.class)
                .setParameter("orderId", orderId)
                .getSingleResult();
    }

    public List<Order> findAllWithProducts(int page, int size) {
//        return em.createQuery(
//                        "select o from Order o " +
//                                "join fetch o.orderProducts op " +
//                                "join fetch op.product", Order.class)
//                .setFirstResult((page - 1) * size)
//                .setMaxResults(size)
//                .getResultList();
        // fetch join + offset 페이지네이션 조합 시 메모리 부족 발생 가능(H000104 경고) 쿼리 수정 + Batchsize로 변경
        return em.createQuery("select o from Order o ", Order.class)
                .setFirstResult((page - 1) * size)
                .setMaxResults(size)
                .getResultList();
    }
}
