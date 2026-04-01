package com.example.precamp.service;

import com.example.precamp.domain.Order;
import com.example.precamp.domain.OrderProduct;
import com.example.precamp.domain.Product;
import com.example.precamp.dto.OrderRequestDto;
import com.example.precamp.repository.OrderProductRepository;
import com.example.precamp.repository.OrderRepository;
import com.example.precamp.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;

    // 등록
    @Transactional
    public Order saveOrder (OrderRequestDto request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalStateException("상품이 없습니다."));

        // (도전 과제) 재고 감소
        product.decreaseStock(request.getOrderQuantity());

        Order order = new Order();
        orderRepository.save(order);

        OrderProduct orderProduct = OrderProduct.create(order, product, request.getOrderQuantity());
        orderProductRepository.save(orderProduct);

        return orderRepository.findByIdWithProducts(order.getId());
    }

    // 단일 조회
    @Transactional(readOnly = true)
    public Order findOrderById (Long orderId) {
        return orderRepository.findByIdWithProducts(orderId);
    }

    // 목록 조회
    @Transactional(readOnly = true)
    public List<Order> findAllOrders (int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return orderRepository.findAllWithProducts(pageable).getContent();
    }
}
