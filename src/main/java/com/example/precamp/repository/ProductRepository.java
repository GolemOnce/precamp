package com.example.precamp.repository;

import com.example.precamp.domain.Product;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class ProductRepository {
//
//    private final EntityManager em;
//
//    public void save(Product product) {
//        if (product.getId() == null) {
//            em.persist(product);
//        } else {
//            em.merge(product);
//        }
//    }
//
//    public void remove(Product product) {
//        em.remove(product);
//    }
//
//    public Product findById(Long id) {
//        return em.find(Product.class, id);
//    }
//
//    public List<Product> findAll() {
//        return em.createQuery("select p from Product p", Product.class).getResultList();
//    }
//
//}

public interface ProductRepository extends JpaRepository<Product, Long> {

}